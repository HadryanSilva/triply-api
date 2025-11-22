package br.com.hadryan.triply.api.usecase.user;

import br.com.hadryan.triply.api.entity.User;

import java.util.UUID;

public interface FindUserByIdUseCase {

    User execute(UUID userId);

}
