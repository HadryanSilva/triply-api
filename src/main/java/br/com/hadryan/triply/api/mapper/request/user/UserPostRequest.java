package br.com.hadryan.triply.api.mapper.request.user;

import jakarta.validation.constraints.NotBlank;

public record UserPostRequest(
        @NotBlank(message = "first name is mandatory") String firstName,
        @NotBlank(message = "last name is mandatory") String lastName,
        @NotBlank(message = "email is mandatory") String email,
        @NotBlank(message = "password is mandatory") String password
) {
}
