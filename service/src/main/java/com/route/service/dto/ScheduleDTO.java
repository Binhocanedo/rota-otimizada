package com.route.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleDTO {

    private String title;
    private OffsetDateTime startAt;
    private OffsetDateTime endAt;
    private Boolean isAvailable;
    private String category;
    private CustomerDTO customer;

    public ScheduleDTO(String title, OffsetDateTime startAt, OffsetDateTime endAt, Boolean isAvailable, String category, CustomerDTO customer) {
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.isAvailable = isAvailable;
        this.category = category;
        this.customer = customer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OffsetDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(OffsetDateTime startAt) {
        this.startAt = startAt;
    }

    public OffsetDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(OffsetDateTime endAt) {
        this.endAt = endAt;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}