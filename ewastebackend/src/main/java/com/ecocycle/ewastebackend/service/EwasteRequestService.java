package com.ecocycle.ewastebackend.service;

import com.ecocycle.ewastebackend.dto.EwasteRequestCreateDto;
import com.ecocycle.ewastebackend.dto.EwasteRequestResponseDto;
import com.ecocycle.ewastebackend.entity.EwasteRequest;
import com.ecocycle.ewastebackend.repository.EwasteRequestRepository;
import com.ecocycle.ewastebackend.util.FileStorageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EwasteRequestService {

    private final EwasteRequestRepository repository;
    private final FileStorageUtil fileStorageUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EwasteRequestService(EwasteRequestRepository repository, FileStorageUtil fileStorageUtil) {
        this.repository = repository;
        this.fileStorageUtil = fileStorageUtil;
    }

    // ✅ Create new e-waste request
    public EwasteRequestResponseDto createRequest(Long userId, EwasteRequestCreateDto dto, MultipartFile[] images) throws Exception {
        // store uploaded files and return their paths
        List<String> storedPaths = fileStorageUtil.storeFiles(userId, images, "ewaste");

        EwasteRequest request = new EwasteRequest();
        request.setUserId(userId);
        request.setDeviceType(dto.getDeviceType());
        request.setBrand(dto.getBrand());
        request.setModel(dto.getModel());
        request.setCondition(dto.getCondition());
        request.setQuantity(dto.getQuantity());
        request.setImagePaths(objectMapper.writeValueAsString(storedPaths));
        request.setPickupAddress(dto.getPickupAddress());
        request.setRemarks(dto.getRemarks());
        request.setStatus("Pending"); // default

        EwasteRequest saved = repository.save(request);
        return mapToDto(saved);
    }

    // ✅ Fetch all requests for a specific user
    public List<EwasteRequestResponseDto> getRequestsForUser(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ Fetch single request by ID
    public EwasteRequestResponseDto getRequest(Long requestId) {
        return repository.findById(requestId)
                .map(this::mapToDto)
                .orElse(null);
    }

    // ✅ Admin updates status (approved/rejected/scheduled)
    public EwasteRequestResponseDto updateStatus(Long requestId, String status, String adminNotes, Date scheduledAt) {
        EwasteRequest request = repository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("E-waste request not found with ID: " + requestId));

        request.setStatus(status);
        request.setAdminNotes(adminNotes);

        if (scheduledAt != null) {
            LocalDateTime scheduledTime = scheduledAt.toInstant()
                    .atZone(TimeZone.getDefault().toZoneId())
                    .toLocalDateTime();
            request.setScheduledAt(scheduledTime);
        }

        EwasteRequest updated = repository.save(request);
        return mapToDto(updated);
    }

    // ✅ Convert Entity → Response DTO
    private EwasteRequestResponseDto mapToDto(EwasteRequest request) {
        EwasteRequestResponseDto dto = new EwasteRequestResponseDto();
        dto.setRequestId(request.getRequestId());
        dto.setUserId(request.getUserId());
        dto.setDeviceType(request.getDeviceType());
        dto.setBrand(request.getBrand());
        dto.setModel(request.getModel());
        dto.setCondition(request.getCondition());
        dto.setQuantity(request.getQuantity());

        try {
            List<String> paths = objectMapper.readValue(request.getImagePaths(), List.class);
            dto.setImagePaths(paths);
        } catch (Exception e) {
            dto.setImagePaths(Collections.emptyList());
        }

        dto.setPickupAddress(request.getPickupAddress());
        dto.setRemarks(request.getRemarks());
        dto.setStatus(request.getStatus());
        dto.setAdminNotes(request.getAdminNotes());
        dto.setScheduledAt(request.getScheduledAt());
        dto.setCreatedAt(request.getCreatedAt());

        return dto;
    }
}
