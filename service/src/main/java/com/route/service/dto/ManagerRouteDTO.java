package com.route.service.dto;

import java.util.List;

public class ManagerRouteDTO {

    private String managerProductionCode;
    private List<RouteStopDTO> route;

    public ManagerRouteDTO(){}

    public ManagerRouteDTO(String managerProductionCode, List<RouteStopDTO> route) {
        this.managerProductionCode = managerProductionCode;
        this.route = route;
    }

    public String getManagerProductionCode() {
        return managerProductionCode;
    }

    public void setManagerProductionCode(String managerProductionCode) {
        this.managerProductionCode = managerProductionCode;
    }

    public List<RouteStopDTO> getRoute() {
        return route;
    }

    public void setRoute(List<RouteStopDTO> route) {
        this.route = route;
    }
}
