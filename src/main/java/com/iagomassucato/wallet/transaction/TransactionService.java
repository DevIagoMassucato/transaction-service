package com.iagomassucato.wallet.transaction;

import com.iagomassucato.wallet.user.UserDomainService;
import com.iagomassucato.wallet.user.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionValidator transactionValidator;
    private final UserDomainService userDomainService;

    @Transactional
    public TransactionResponse create(TransactionRequest transactionRequest){

        UserEntity sender = userDomainService.findById(transactionRequest.senderId());
        UserEntity receiver = userDomainService.findById(transactionRequest.receiverId());

        transactionValidator.validateTransaction(sender);

        sender.debit(transactionRequest.value());
        receiver.credit(transactionRequest.value());

        TransactionEntity transactionEntity = new TransactionEntity(
                transactionRequest.value(),
                sender,
                receiver
        );

        transactionRepository.save(transactionEntity);
        userDomainService.save(sender);
        userDomainService.save(receiver);

        return TransactionResponse.fromEntity(transactionEntity);
    }
}
