package br.com.hadryan.triply.api.mapper.response.user;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String email
) {
}
