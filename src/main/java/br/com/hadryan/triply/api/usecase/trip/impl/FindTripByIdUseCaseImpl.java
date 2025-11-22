package br.com.hadryan.triply.api.usecase.trip.impl;

import br.com.hadryan.triply.api.entity.Trip;
import br.com.hadryan.triply.api.exception.NotFoundException;
import br.com.hadryan.triply.api.repository.TripRepository;
import br.com.hadryan.triply.api.usecase.trip.FindTripByIdUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindTripByIdUseCaseImpl implements FindTripByIdUseCase {

    private final TripRepository tripRepository;

    public FindTripByIdUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip execute(UUID id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trip not found!"));
    }

}
