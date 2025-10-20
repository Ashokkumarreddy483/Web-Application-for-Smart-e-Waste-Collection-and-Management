package com.ecocycle.ewastebackend.dto;

public class EwasteRequestCreateDto {
    private String deviceType;
    private String brand;
    private String model;
    private String condition;
    private Integer quantity;
    private String pickupAddress;
    private String remarks;
    // getters/setters

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public EwasteRequestCreateDto(String deviceType, String brand, String model, String condition, Integer quantity, String pickupAddress, String remarks) {
        this.deviceType = deviceType;
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.quantity = quantity;
        this.pickupAddress = pickupAddress;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "EwasteRequestCreateDto{" +
                "deviceType='" + deviceType + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", condition='" + condition + '\'' +
                ", quantity=" + quantity +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
