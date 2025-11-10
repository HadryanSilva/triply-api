package br.com.hadryan.triply.usecase.impl;

import br.com.hadryan.triply.domain.entity.Trip;
import br.com.hadryan.triply.exception.NotFoundException;
import br.com.hadryan.triply.repository.TripRepository;
import br.com.hadryan.triply.usecase.GetTripByIdUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetTripByIdUseCaseImpl implements GetTripByIdUseCase {

    private static final Logger log = LoggerFactory.getLogger(GetTripByIdUseCaseImpl.class);

    private final TripRepository tripRepository;

    public GetTripByIdUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip execute(UUID id) {
        log.info("finding trip by id {}", id);
        return tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trip not found"));
    }

}
