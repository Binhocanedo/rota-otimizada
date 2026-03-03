package com.route.service.dto;

public class AddressDTO {
    private CoordinatesDTO coordinates;

    public AddressDTO(CoordinatesDTO coordinates) {
        this.coordinates = coordinates;
    }

    public CoordinatesDTO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDTO coordinates) {
        this.coordinates = coordinates;
    }
}
