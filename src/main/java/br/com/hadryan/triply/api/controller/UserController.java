package br.com.hadryan.triply.api.controller;

import br.com.hadryan.triply.api.mapper.UserMapper;
import br.com.hadryan.triply.api.mapper.request.user.UserPostRequest;
import br.com.hadryan.triply.api.mapper.response.user.UserResponse;
import br.com.hadryan.triply.api.usecase.user.CreateUserUseCase;
import br.com.hadryan.triply.api.usecase.user.FindUserByIdUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserMapper userMapper;

    private final CreateUserUseCase createUserUseCase;

    private final FindUserByIdUseCase findUserByIdUseCase;

    public UserController(UserMapper userMapper,
                          CreateUserUseCase createUserUseCase,
                          FindUserByIdUseCase findUserByIdUseCase
    ) {
        this.userMapper = userMapper;
        this.createUserUseCase = createUserUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        var userFound = findUserByIdUseCase.execute(id);
        return ResponseEntity.ok(userMapper.userToResponse(userFound));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserPostRequest request) {
        var userToSave = userMapper.postToUser(request);
        var userCreated = createUserUseCase.execute(userToSave);
        return ResponseEntity.created(URI.create("/api/v1/users/" + userCreated.getId()))
                .body(userMapper.userToResponse(userCreated));
    }

}
