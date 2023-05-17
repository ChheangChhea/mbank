package co.istad.mbanking.api.auth.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterDto(
        @NotBlank(message = "Email is required!") @Email String email,
        @NotBlank(message = "Password is request!") String password,
        @NotBlank(message = "confirmedPassword password is request!") String confirmedPassword,
        @NotNull(message = "roleIds password is request!") List<Integer> roleIds) {
}

