package com.ecocycle.ewastebackend.controller;

import com.ecocycle.ewastebackend.dto.EwasteRequestCreateDto;
import com.ecocycle.ewastebackend.dto.EwasteRequestResponseDto;
import com.ecocycle.ewastebackend.service.EwasteRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "http://localhost:3000")
public class EwasteRequestController {

    private final EwasteRequestService service;

    public EwasteRequestController(EwasteRequestService service) {
        this.service = service;
    }

    /**
     * Create new e-waste request (multipart/form-data)
     * Accepts form fields + multiple image files under "images"
     */
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createRequest(
            @RequestPart("data") @Valid EwasteRequestCreateDto dto,
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            Principal principal
    ) {
        try {
            // If using JWT, get userId via principal or userService. For now: hardcoded test user id fallback
            Long userId = getUserIdFromPrincipal(principal);
            var created = service.createRequest(userId, dto, images);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Create request failed: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getRequestsForUser(Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<EwasteRequestResponseDto> list = service.getRequestsForUser(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequest(@PathVariable("id") Long id, Principal principal) {
        var dto = service.getRequest(id);
        if (dto == null) return ResponseEntity.notFound().build();
        // optional: check ownership or admin permission
        return ResponseEntity.ok(dto);
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        // If you have JWT: principal.getName() returns username -> fetch user id via UserService
        // For testing/hardcoded
        Long fallbackUserId = 1L;
        if (principal == null) return fallbackUserId;
        try {
            // Example: username -> userService findByUsername -> user.getId()
            // For now, fallback:
            return fallbackUserId;
        } catch (Exception e) {
            return fallbackUserId;
        }
    }
}
