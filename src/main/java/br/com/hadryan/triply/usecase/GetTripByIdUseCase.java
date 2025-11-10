package br.com.hadryan.triply.usecase;

import br.com.hadryan.triply.domain.entity.Trip;

import java.util.UUID;

public interface GetTripByIdUseCase {

    public Trip execute(UUID id);

}
