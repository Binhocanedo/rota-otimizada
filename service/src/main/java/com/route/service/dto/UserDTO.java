package com.route.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String productionCode;
    private String managerName;
    private List<ScheduleDTO> schedule;

    public UserDTO(String productionCode, String managerName, List<ScheduleDTO> schedule) {
        this.productionCode = productionCode;
        this.managerName = managerName;
        this.schedule = schedule;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<ScheduleDTO> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleDTO> schedule) {
        this.schedule = schedule;
    }
}
