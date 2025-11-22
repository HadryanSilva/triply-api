package br.com.hadryan.triply.api.usecase.trip;

import br.com.hadryan.triply.api.entity.Trip;

public interface CreateTripUseCase {

    Trip execute(Trip trip);

}
