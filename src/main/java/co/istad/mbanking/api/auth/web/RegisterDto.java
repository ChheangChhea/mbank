package co.istad.mbanking.api.auth.web;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
                          @NotBlank(message = "Email is require!")
                          String email,
                          @NotBlank(message = "Password is request!")
                          String password,
                          @NotBlank(message = "Config password is request!")
                          String confirmedPassword) {
}
