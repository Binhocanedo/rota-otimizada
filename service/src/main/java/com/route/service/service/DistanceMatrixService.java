package com.route.service.service;

import com.route.service.client.OpenRouteClient;
import com.route.service.dto.request.RouteRequestDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistanceMatrixService {

    private final OpenRouteClient openRouteClient;
    public DistanceMatrixService(OpenRouteClient openRouteClient) {
        this.openRouteClient = openRouteClient;
    }
    public double[][] generateMatrix(RouteRequestDTO request) {

        List<double[]> coordinates = new ArrayList<>();

        // 1️⃣ Adiciona ponto inicial do gerente
        coordinates.add(new double[]{
                request.getManager().getStartLng(),
                request.getManager().getStartLat()
        });

        // 2️⃣ Adiciona lojas
        request.getStores().forEach(store ->
                coordinates.add(new double[]{
                        store.getLng(),
                        store.getLat()
                })
        );
        return openRouteClient.getDistanceMatrix(coordinates);
    }
}
