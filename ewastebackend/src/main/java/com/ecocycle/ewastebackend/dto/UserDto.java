package com.ecocycle.ewastebackend.dto;

import com.ecocycle.ewastebackend.entity.Role;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String username;

    private String firstName;      // Added
    private String lastName;       // Added
    private String fullName;       // Combined convenience field

    private String email;
    private String phoneNumber;
    private String pickupAddress;
    private String profilePictureUrl;

    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getFullName() {
        return (firstName != null ? firstName : "") + (lastName != null ? " " + lastName : "");
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.isBlank()) {
            String[] names = fullName.trim().split("\\s+", 2);
            this.firstName = names[0];
            this.lastName = names.length > 1 ? names[1] : "";
        }
    }
}
