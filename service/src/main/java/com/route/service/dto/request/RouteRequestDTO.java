package com.route.service.dto.request;

import com.route.service.dto.ManagerDTO;
import com.route.service.dto.StoreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class RouteRequestDTO {
    private ManagerDTO manager;
    private List<StoreDTO> stores;

    public RouteRequestDTO(){}

    public RouteRequestDTO(ManagerDTO manager, List<StoreDTO> stores) {
        this.manager = manager;
        this.stores = stores;
    }

    public ManagerDTO getManager() {
        return manager;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }

    public List<StoreDTO> getStores() {
        return stores;
    }

    public void setStores(List<StoreDTO> stores) {
        this.stores = stores;
    }
}