package br.com.hadryan.triply.mapper.response;

import java.time.LocalDate;
import java.util.UUID;

public record TripResponse(
        UUID id,
        String city,
        String country,
        LocalDate arrival,
        LocalDate departure)
{
}
