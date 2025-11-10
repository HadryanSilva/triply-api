package br.com.hadryan.triply.usecase;

import br.com.hadryan.triply.domain.entity.Trip;

public interface CreateTripUseCase {

    Trip execute(Trip trip);

}
