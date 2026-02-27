package com.route.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OptimizedRouteDTO {
    private String storeId;
    private String storeName;
    private int sequence;
    private String scheduledTime; // HH:mm

    public OptimizedRouteDTO(){}

    public OptimizedRouteDTO(String storeId, String storeName, int sequence, String scheduledTime) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.sequence = sequence;
        this.scheduledTime = scheduledTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}