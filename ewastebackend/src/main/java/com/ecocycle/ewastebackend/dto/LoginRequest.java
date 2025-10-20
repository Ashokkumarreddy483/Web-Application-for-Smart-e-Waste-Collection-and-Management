package com.ecocycle.ewastebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username cannot be empty")
    @Email(message = "Username must be a valid email format")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}