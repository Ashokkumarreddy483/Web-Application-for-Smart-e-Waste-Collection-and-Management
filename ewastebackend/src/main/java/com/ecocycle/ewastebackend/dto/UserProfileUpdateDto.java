package com.ecocycle.ewastebackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserProfileUpdateDto {

    @NotBlank(message = "Full name cannot be empty")
    private String fullName;

    private String email;
    private String phoneNumber;
    private String pickupAddress;
    private String profilePictureUrl;
}
