package com.route.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ManagerDTO {
    private String id;
    private String name;
    private double startLat;
    private double startLng;
    private String startWork; // HH:mm
    private String endWork;   // HH:mm

    public ManagerDTO(){}

    public ManagerDTO(String id, String name, double startLat, double startLng, String startWork, String endWork) {
        this.id = id;
        this.name = name;
        this.startLat = startLat;
        this.startLng = startLng;
        this.startWork = startWork;
        this.endWork = endWork;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLng() {
        return startLng;
    }

    public void setStartLng(double startLng) {
        this.startLng = startLng;
    }

    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public String getEndWork() {
        return endWork;
    }

    public void setEndWork(String endWork) {
        this.endWork = endWork;
    }
}