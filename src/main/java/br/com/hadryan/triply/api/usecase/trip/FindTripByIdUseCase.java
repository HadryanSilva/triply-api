package br.com.hadryan.triply.api.usecase.trip;

import br.com.hadryan.triply.api.entity.Trip;

import java.util.UUID;

public interface FindTripByIdUseCase {

    Trip execute(UUID id);

}
