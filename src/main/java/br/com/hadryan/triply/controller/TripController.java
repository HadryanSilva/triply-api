package br.com.hadryan.triply.controller;

import br.com.hadryan.triply.mapper.TripMapper;
import br.com.hadryan.triply.mapper.request.TripPostRequest;
import br.com.hadryan.triply.mapper.response.TripResponse;
import br.com.hadryan.triply.usecase.CreateTripUseCase;
import br.com.hadryan.triply.usecase.GetTripByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final TripMapper tripMapper;

    private final CreateTripUseCase createTripUseCase;

    private final GetTripByIdUseCase getTripByIdUseCase;

    public TripController(
            TripMapper tripMapper,
            CreateTripUseCase createTripUseCase,
            GetTripByIdUseCase getTripByIdUseCase
    ) {
        this.tripMapper = tripMapper;
        this.createTripUseCase = createTripUseCase;
        this.getTripByIdUseCase = getTripByIdUseCase;
    }

    @Operation(summary = "Find trip by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return trip found",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TripResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Trip not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findById(
            @Parameter(description = "id of trip to be searched")
            @PathVariable UUID id
    ) {
        var trip = getTripByIdUseCase.execute(id);
        return ResponseEntity.ok(tripMapper.tripToResponse(trip));
    }

    @Operation(summary = "Create a new Trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create the trip",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TripResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "One or more fields are invalid",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    ))
    })
    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody @Valid TripPostRequest request) {
        var trip = createTripUseCase.execute(tripMapper.postToTrip(request));
        return ResponseEntity.created(URI.create("/api/v1/trips/" + trip.getId()))
                .body(tripMapper.tripToResponse(trip));
    }

}
