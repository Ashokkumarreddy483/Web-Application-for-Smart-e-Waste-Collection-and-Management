package com.ecocycle.ewastebackend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ewaste_requests")
public class EwasteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private Long userId;

    private String deviceType;
    private String brand;
    private String model;

    @Column(name = "`condition`") // ✅ Escaped because 'condition' is a reserved keyword
    private String condition;

    private Integer quantity;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String imagePaths; // JSON array string

    private String pickupAddress;
    private String remarks;
    private String status; // Pending, Approved, Rejected, Scheduled

    @Lob
    private String adminNotes;

    private LocalDateTime scheduledAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ✅ Required no-args constructor for JPA
    public EwasteRequest() {}

    // ✅ Parameterized constructor
    public EwasteRequest(Long requestId, Long userId, String deviceType, String brand, String model, String condition,
                         Integer quantity, String imagePaths, String pickupAddress, String remarks,
                         String status, String adminNotes, LocalDateTime scheduledAt,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
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
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (status == null) status = "Pending";
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
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

    public String getImagePaths() { return imagePaths; }
    public void setImagePaths(String imagePaths) { this.imagePaths = imagePaths; }

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

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "EwasteRequest{" +
                "requestId=" + requestId +
                ", userId=" + userId +
                ", deviceType='" + deviceType + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", condition='" + condition + '\'' +
                ", quantity=" + quantity +
                ", imagePaths='" + imagePaths + '\'' +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                ", adminNotes='" + adminNotes + '\'' +
                ", scheduledAt=" + scheduledAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
