package br.com.hadryan.triply.api.usecase.user.impl;

import br.com.hadryan.triply.api.entity.User;
import br.com.hadryan.triply.api.exception.NotFoundException;
import br.com.hadryan.triply.api.repository.UserRepository;
import br.com.hadryan.triply.api.usecase.user.FindUserByIdUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final UserRepository userRepository;

    public FindUserByIdUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }
}
