package com.ecocycle.ewastebackend.controller;

import com.ecocycle.ewastebackend.dto.UserDto;
import com.ecocycle.ewastebackend.dto.UserProfileUpdateDto;
import com.ecocycle.ewastebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** GET /api/users/profile */
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile() {
        // Return a default dummy user
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("ashok");
        userDto.setFullName("Ashok Kumar");
        userDto.setEmail("ashok@example.com");
        userDto.setPhoneNumber("1234567890");
        userDto.setPickupAddress("123 Street, City");
        return ResponseEntity.ok(userDto);
    }

    /** PUT /api/users/profile */
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUserProfile(@Valid @RequestBody UserProfileUpdateDto updateDto) {
        // For token-free: update default user id=1
        UserDto updatedUser = userService.updateUserProfile(1L, updateDto);
        return ResponseEntity.ok(updatedUser);
    }
}
