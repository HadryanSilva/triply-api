package br.com.hadryan.triply.api.controller;

import br.com.hadryan.triply.api.mapper.TripMapper;
import br.com.hadryan.triply.api.mapper.request.trip.TripPostRequest;
import br.com.hadryan.triply.api.mapper.response.trip.TripResponse;
import br.com.hadryan.triply.api.usecase.trip.CreateTripUseCase;
import br.com.hadryan.triply.api.usecase.trip.FindTripByIdUseCase;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final TripMapper tripMapper;

    private final CreateTripUseCase createTripUseCase;

    private final FindTripByIdUseCase findTripByIdUseCase;

    public TripController(TripMapper tripMapper,
                          CreateTripUseCase createTripUseCase,
                          FindTripByIdUseCase findTripByIdUseCase
    ) {
        this.tripMapper = tripMapper;
        this.createTripUseCase = createTripUseCase;
        this.findTripByIdUseCase = findTripByIdUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull TripResponse> findById(@PathVariable UUID id) {
        var tripFound =findTripByIdUseCase.execute(id);
        return ResponseEntity.ok(tripMapper.tripToResponse(tripFound));
    }

    @PostMapping
    public ResponseEntity<@NonNull TripResponse> create(@RequestBody @Valid TripPostRequest request) {
        var tripToSave = tripMapper.postToTrip(request);
        var tripCreated = createTripUseCase.execute(tripToSave);
        return ResponseEntity.created(URI.create("/api/v1/trips" + tripCreated.getId()))
                .body(tripMapper.tripToResponse(tripCreated));
    }

}
