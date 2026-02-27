package com.route.service.service;

import com.route.service.dto.OptimizedRouteDTO;
import com.route.service.dto.StoreDTO;
import com.route.service.dto.request.RouteRequestDTO;
import com.route.service.dto.response.RouteResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class RouteServiceImpl implements RouteService {

    private final DistanceMatrixService distanceMatrixService;
    private final RouteOptimizationService routeOptimizationService;

    public RouteServiceImpl(DistanceMatrixService distanceMatrixService, RouteOptimizationService routeOptimizationService) {
        this.distanceMatrixService = distanceMatrixService;
        this.routeOptimizationService = routeOptimizationService;
    }

    @Override
    public RouteResponseDTO calculateOptimizedRoute(RouteRequestDTO request) {

        // 1️⃣ Gerar matriz
        double[][] matrix = distanceMatrixService.generateMatrix(request);

        // 2️⃣ Rota inicial
        List<Integer> route = routeOptimizationService.nearestNeighbor(matrix);

        // 3️⃣ Melhorar com 2-opt
        route = routeOptimizationService.twoOpt(route, matrix);

        // 4️⃣ Calcular distância total (metros → km)
        double totalDistanceMeters =
                routeOptimizationService.calculateTotalDistance(route, matrix);

        double totalDistanceKm = totalDistanceMeters / 1000.0;

        // 5️⃣ Calcular horários
        List<OptimizedRouteDTO> optimizedRoute =
                buildOptimizedRoute(route, request);

        return new RouteResponseDTO(
                request.getManager().getId(),
                request.getManager().getName(),
                totalDistanceKm,
                optimizedRoute
        );
    }

    private List<OptimizedRouteDTO> buildOptimizedRoute(
            List<Integer> route,
            RouteRequestDTO request) {

        List<OptimizedRouteDTO> result = new ArrayList<>();

        LocalTime currentTime =
                LocalTime.parse(request.getManager().getStartWork());

        LocalTime endWork =
                LocalTime.parse(request.getManager().getEndWork());

        int sequence = 1;

        // Começa do índice 1 (ignora gerente posição 0)
        for (int i = 1; i < route.size() - 1; i++) {

            int storeIndex = route.get(i);

            // Ajuste porque índice 0 é gerente
            StoreDTO store = request.getStores().get(storeIndex - 1);

            // 30 min deslocamento
            currentTime = currentTime.plusMinutes(30);

            if (currentTime.isAfter(endWork)) {
                break;
            }

            // horário agendado é após deslocamento
            String scheduledTime = currentTime.toString();

            // +15 min visita
            currentTime = currentTime.plusMinutes(15);

            result.add(new OptimizedRouteDTO(
                    store.getId(),
                    store.getName(),   // ✅ adicionando nome
                    sequence++,
                    scheduledTime
            ));
        }
        return result;
    }
}