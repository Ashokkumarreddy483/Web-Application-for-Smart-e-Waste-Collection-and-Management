package com.ecocycle.ewastebackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EwasteRequestResponseDto {
    private Long requestId;
    private Long userId;
    private String deviceType;
    private String brand;
    private String model;
    private String condition;
    private Integer quantity;
    private List<String> imagePaths;
    private String pickupAddress;
    private String remarks;
    private String status;
    private String adminNotes;
    private LocalDateTime scheduledAt;
    private LocalDateTime createdAt;

    // ✅ Default constructor (required)
    public EwasteRequestResponseDto() {
    }

    // ✅ Parameterized constructor
    public EwasteRequestResponseDto(Long requestId, Long userId, String deviceType, String brand, String model,
                                    String condition, Integer quantity, List<String> imagePaths,
                                    String pickupAddress, String remarks, String status, String adminNotes,
                                    LocalDateTime scheduledAt, LocalDateTime createdAt) {
        this.requestId = requestId;
        this.userId = userId;
        this.deviceType = deviceType;
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.quantity = quantity;
        this.imagePaths = imagePaths;
        this.pickupAddress = pickupAddress;
        this.remarks = remarks;
        this.status = status;
        this.adminNotes = adminNotes;
        this.scheduledAt = scheduledAt;
        this.createdAt = createdAt;
    }

    // ✅ Getters and Setters
    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public List<String> getImagePaths() { return imagePaths; }
    public void setImagePaths(List<String> imagePaths) { this.imagePaths = imagePaths; }

    public String getPickupAddress() { return pickupAddress; }
    public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAdminNotes() { return adminNotes; }
    public void setAdminNotes(String adminNotes) { this.adminNotes = adminNotes; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "EwasteRequestResponseDto{" +
                "requestId=" + requestId +
                ", userId=" + userId +
                ", deviceType='" + deviceType + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", condition='" + condition + '\'' +
                ", quantity=" + quantity +
                ", imagePaths=" + imagePaths +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                ", adminNotes='" + adminNotes + '\'' +
                ", scheduledAt=" + scheduledAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
