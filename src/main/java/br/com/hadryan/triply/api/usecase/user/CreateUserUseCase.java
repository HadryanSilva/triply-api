package br.com.hadryan.triply.api.usecase.user;

import br.com.hadryan.triply.api.entity.User;

public interface CreateUserUseCase {

    User execute(User user);

}
