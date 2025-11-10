package br.com.hadryan.triply.mapper.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TripPostRequest(
        @NotBlank(message = "city is mandatory")
        String city,

        @NotBlank(message = "country is mandatory")
        String country,

        @NotNull(message = "arrival is mandatory")
        LocalDate arrival,
        LocalDate departure
) {
}
