package com.ecocycle.ewastebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken; // For refreshing tokens later
    private String username;
    private String role;
    private Long userId;
}