package br.com.hadryan.triply.api.mapper.request.trip;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record TripPostRequest(
        @NotNull(message = "userId is mandatory") UUID userId,
        @NotBlank(message = "city is mandatory") String city,
        @NotBlank(message = "country is mandatory") String country,
        String description,
        @NotNull(message = "start date is mandatory") LocalDate startDate,
        LocalDate endDate
) {
}
