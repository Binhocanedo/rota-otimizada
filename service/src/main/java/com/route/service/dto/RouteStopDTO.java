package com.route.service.dto;

import java.time.LocalTime;

public class RouteStopDTO {

    private String customerName;
    private LocalTime arrivalAt;
    private LocalTime departureAt;
    private Integer visitDurationMinutes;

    public RouteStopDTO(){}

    public RouteStopDTO(String customerName, LocalTime arrivalAt, LocalTime departureAt, Integer visitDurationMinutes) {
        this.customerName = customerName;
        this.arrivalAt = arrivalAt;
        this.departureAt = departureAt;
        this.visitDurationMinutes = visitDurationMinutes;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalTime getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(LocalTime arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    public LocalTime getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(LocalTime departureAt) {
        this.departureAt = departureAt;
    }

    public Integer getVisitDurationMinutes() {
        return visitDurationMinutes;
    }

    public void setVisitDurationMinutes(Integer visitDurationMinutes) {
        this.visitDurationMinutes = visitDurationMinutes;
    }
}
