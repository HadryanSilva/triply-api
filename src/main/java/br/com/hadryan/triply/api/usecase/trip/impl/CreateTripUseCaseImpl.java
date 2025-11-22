package br.com.hadryan.triply.api.usecase.trip.impl;

import br.com.hadryan.triply.api.entity.Trip;
import br.com.hadryan.triply.api.exception.BusinessException;
import br.com.hadryan.triply.api.repository.TripRepository;
import br.com.hadryan.triply.api.usecase.trip.CreateTripUseCase;
import br.com.hadryan.triply.api.usecase.user.FindUserByIdUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateTripUseCaseImpl implements CreateTripUseCase {

    private final TripRepository tripRepository;

    private final FindUserByIdUseCase findUserByIdUseCase;

    public CreateTripUseCaseImpl(TripRepository tripRepository,
                                 FindUserByIdUseCase findUserByIdUseCase
    ) {
        this.tripRepository = tripRepository;
        this.findUserByIdUseCase = findUserByIdUseCase;
    }

    @Override
    public Trip execute(Trip trip) {
        validateDate(trip);
        var user = findUserByIdUseCase.execute(trip.getUser().getId());
        trip.setUser(user);
        return tripRepository.save(trip);
    }

    private void validateDate(Trip trip) {
        if (trip.getStartDate().isAfter(trip.getEndDate())) {
            throw new BusinessException("Start date is after end date");
        }
    }
}
