package br.com.hadryan.triply.api.mapper.response.trip;

import java.time.LocalDate;
import java.util.UUID;

public record TripResponse(
        UUID id,
        String city,
        String country,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
