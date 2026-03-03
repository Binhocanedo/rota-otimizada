package com.route.service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.route.service.dto.RegionalInfoDTO;
import com.route.service.dto.UserDTO;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionalRequestDTO {

    private RegionalInfoDTO regionalInfo;
    private List<UserDTO> users;

    public RegionalRequestDTO(RegionalInfoDTO regionalInfo, List<UserDTO> users) {
        this.regionalInfo = regionalInfo;
        this.users = users;
    }

    public RegionalInfoDTO getRegionalInfo() {
        return regionalInfo;
    }

    public void setRegionalInfo(RegionalInfoDTO regionalInfo) {
        this.regionalInfo = regionalInfo;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
