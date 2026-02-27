package com.route.service.dto.response;

import com.route.service.dto.OptimizedRouteDTO;
import com.route.service.dto.request.RouteRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class RouteResponseDTO {
    private String managerId;
    private String managerName;
    private double totalDistanceKm;
    private List<OptimizedRouteDTO> optimizedRoute;

    public RouteResponseDTO(){}

    public RouteResponseDTO(String managerId, String managerName, double totalDistanceKm, List<OptimizedRouteDTO> optimizedRoute) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.totalDistanceKm = totalDistanceKm;
        this.optimizedRoute = optimizedRoute;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public double getTotalDistanceKm() {
        return totalDistanceKm;
    }

    public void setTotalDistanceKm(double totalDistanceKm) {
        this.totalDistanceKm = totalDistanceKm;
    }

    public List<OptimizedRouteDTO> getOptimizedRoute() {
        return optimizedRoute;
    }

    public void setOptimizedRoute(List<OptimizedRouteDTO> optimizedRoute) {
        this.optimizedRoute = optimizedRoute;
    }
}
