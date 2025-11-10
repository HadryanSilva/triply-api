package br.com.hadryan.triply.usecase.impl;

import br.com.hadryan.triply.domain.entity.Trip;
import br.com.hadryan.triply.repository.TripRepository;
import br.com.hadryan.triply.usecase.CreateTripUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateTripUseCaseImpl implements CreateTripUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateTripUseCaseImpl.class);

    private final TripRepository tripRepository;

    public CreateTripUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip execute(Trip trip) {
        log.info("creating trip...");
        return tripRepository.save(trip);
    }

}
