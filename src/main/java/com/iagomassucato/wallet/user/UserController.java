package com.iagomassucato.wallet.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.create(userRequest);
        return new ResponseEntity<>(
                userResponse,
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(userService.findById(id));
    }
}
