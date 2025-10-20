package com.ecocycle.ewastebackend.controller;

import com.ecocycle.ewastebackend.dto.UserDto;
import com.ecocycle.ewastebackend.dto.UserProfileUpdateDto;
import com.ecocycle.ewastebackend.entity.User;
import com.ecocycle.ewastebackend.exception.ResourceNotFoundException;
import com.ecocycle.ewastebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /** GET /api/auth/profile */
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        try {
            // Token-free default user
            UserDto userDto = userService.getUserProfileByUsername("ashok");
            return ResponseEntity.ok(userDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found: " + e.getMessage());
        }
    }

    /** PUT /api/auth/profile */
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UserProfileUpdateDto updateDto) {
        try {
            // Token-free default user ID = 1
            UserDto updatedUser = userService.updateUserProfile(1L, updateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Profile update failed: " + e.getMessage());
        }
    }

    /** POST /api/auth/register */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserProfileUpdateDto registerDto) {
        try {
            User user = userService.registerNewUser(
                    "ashok", // default username
                    "password", // default password
                    registerDto.getFullName().split(" ")[0], // firstName
                    registerDto.getFullName().contains(" ") ? registerDto.getFullName().split(" ")[1] : "",
                    registerDto.getPhoneNumber()
            );
            UserDto dto = userService.getUserProfileByUsername(user.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }
}
