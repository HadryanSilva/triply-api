package br.com.hadryan.triply.api.usecase.user.impl;

import br.com.hadryan.triply.api.entity.User;
import br.com.hadryan.triply.api.repository.UserRepository;
import br.com.hadryan.triply.api.usecase.user.CreateUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(User user) {
        return userRepository.save(user);
    }
}
