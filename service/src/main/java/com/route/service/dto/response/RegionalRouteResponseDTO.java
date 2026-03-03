package com.route.service.dto.response;

import com.route.service.dto.ManagerRouteDTO;

import java.util.List;

public class RegionalRouteResponseDTO {

    private String regionalCode;
    private List<ManagerRouteDTO> routes;

    public RegionalRouteResponseDTO(){}

    public RegionalRouteResponseDTO(String regionalCode, List<ManagerRouteDTO> routes) {
        this.regionalCode = regionalCode;
        this.routes = routes;
    }

    public String getRegionalCode() {
        return regionalCode;
    }

    public void setRegionalCode(String regionalCode) {
        this.regionalCode = regionalCode;
    }

    public List<ManagerRouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<ManagerRouteDTO> routes) {
        this.routes = routes;
    }
}
