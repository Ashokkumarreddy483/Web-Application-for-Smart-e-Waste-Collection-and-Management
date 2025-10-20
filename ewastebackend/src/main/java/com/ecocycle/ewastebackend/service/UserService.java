package com.ecocycle.ewastebackend.service;

import com.ecocycle.ewastebackend.dto.UserDto;
import com.ecocycle.ewastebackend.dto.UserProfileUpdateDto;
import com.ecocycle.ewastebackend.entity.Role;
import com.ecocycle.ewastebackend.entity.User;
import com.ecocycle.ewastebackend.exception.ResourceNotFoundException;
import com.ecocycle.ewastebackend.exception.UserAlreadyExistsException;
import com.ecocycle.ewastebackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // -------------------- REGISTER NEW USER --------------------
    @Transactional
    public User registerNewUser(String username, String password, String firstName, String lastName, String contactNumber) {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("User with username " + username + " already exists.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setContactNumber(contactNumber);
        newUser.setRole(Role.USER);

        return userRepository.save(newUser);
    }

    // -------------------- GET USER PROFILE BY USERNAME --------------------
    @Transactional(readOnly = true)
    public UserDto getUserProfileByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return mapUserToUserDto(user);
    }

    // -------------------- UPDATE USER PROFILE --------------------
    @Transactional
    public UserDto updateUserProfile(Long userId, UserProfileUpdateDto updateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Set full name
        if (updateDto.getFullName() != null && !updateDto.getFullName().isBlank()) {
            user.setFirstName(updateDto.getFullName().split("\\s+")[0]);
            String[] names = updateDto.getFullName().split("\\s+", 2);
            user.setLastName(names.length > 1 ? names[1] : "");
        }

        if (updateDto.getPhoneNumber() != null) {
            user.setContactNumber(updateDto.getPhoneNumber());
        }
        if (updateDto.getEmail() != null) {
            user.setEmail(updateDto.getEmail());
        }
        if (updateDto.getPickupAddress() != null) {
            user.setPickupAddress(updateDto.getPickupAddress());
        }
        if (updateDto.getProfilePictureUrl() != null) {
            user.setProfilePictureUrl(updateDto.getProfilePictureUrl());
        }

        User updatedUser = userRepository.save(user);
        return mapUserToUserDto(updatedUser);
    }

    // -------------------- HELPER: MAP USER TO DTO --------------------
    private UserDto mapUserToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setFullName(user.getFirstName() + (user.getLastName() != null ? " " + user.getLastName() : ""));
        dto.setPhoneNumber(user.getContactNumber());
        dto.setEmail(user.getEmail());
        dto.setPickupAddress(user.getPickupAddress());
        dto.setProfilePictureUrl(user.getProfilePictureUrl());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    // -------------------- PASSWORD CHECK --------------------
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPasswordHash());
    }
}
