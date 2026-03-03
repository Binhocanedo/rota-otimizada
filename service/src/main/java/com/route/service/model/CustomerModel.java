package com.route.service.model;

public class CustomerModel {

    private String name;
    private Double lat;
    private Double lng;
    private Integer visitDurationMinutes;

    public CustomerModel(){}

    public CustomerModel(String name, Double lat, Double lng, Integer visitDurationMinutes) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.visitDurationMinutes = visitDurationMinutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getVisitDurationMinutes() {
        return visitDurationMinutes;
    }

    public void setVisitDurationMinutes(Integer visitDurationMinutes) {
        this.visitDurationMinutes = visitDurationMinutes;
    }
}
