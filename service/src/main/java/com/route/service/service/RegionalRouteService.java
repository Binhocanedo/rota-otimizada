package com.route.service.service;

import com.route.service.dto.ManagerRouteDTO;
import com.route.service.dto.RouteStopDTO;
import com.route.service.dto.UserDTO;
import com.route.service.dto.request.RegionalRequestDTO;
import com.route.service.dto.response.RegionalRouteResponseDTO;
import com.route.service.model.CustomerModel;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegionalRouteService {

    private final RouteOptimizationService routeOptimizationService;
    private final ScheduleSimulationService scheduleSimulationService;

    public RegionalRouteService(
            RouteOptimizationService routeOptimizationService,
            ScheduleSimulationService scheduleSimulationService) {
        this.routeOptimizationService = routeOptimizationService;
        this.scheduleSimulationService = scheduleSimulationService;
    }

    private double[][] buildDistanceMatrix(List<CustomerModel> customers) {

        int size = customers.size();
        double[][] matrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    double dx = customers.get(i).getLat() - customers.get(j).getLat();
                    double dy = customers.get(i).getLng() - customers.get(j).getLng();
                    matrix[i][j] = Math.sqrt(dx * dx + dy * dy);
                }
            }
        }

        return matrix;
    }

    private List<CustomerModel> mapIndexesToCustomers(
            List<Integer> routeIndexes,
            List<CustomerModel> customers) {

        List<CustomerModel> ordered = new ArrayList<>();

        for (int i = 0; i < routeIndexes.size(); i++) {

            int index = routeIndexes.get(i);

            // Ignorar o último 0 (retorno ao início)
            if (i == routeIndexes.size() - 1 && index == 0) {
                break;
            }

            ordered.add(customers.get(index));
        }

        return ordered;
    }

    public RegionalRouteResponseDTO generateRoutes(RegionalRequestDTO request) {

        List<ManagerRouteDTO> managerRoutes = new ArrayList<>();

        for (UserDTO user : request.getUsers()) {

            List<CustomerModel> customers = extractCustomers(user);

            // 1️⃣ Gera matriz de distância
            double[][] matrix = buildDistanceMatrix(customers);

            // 2️⃣ Rota inicial
            List<Integer> routeIndexes =
                    routeOptimizationService.nearestNeighbor(matrix);

            // 3️⃣ Melhora com 2-opt
            routeIndexes =
                    routeOptimizationService.twoOpt(routeIndexes, matrix);
            // 4️⃣ Converter índices em lista ordenada
            List<CustomerModel> ordered =
                    mapIndexesToCustomers(routeIndexes, customers);

            // 2️⃣ Simular agenda
            List<RouteStopDTO> schedule =
                    scheduleSimulationService.simulate(
                            ordered,
                            LocalTime.of(12, 0),
                            LocalTime.of(13, 0)
                    );

            ManagerRouteDTO managerRoute = new ManagerRouteDTO();
            managerRoute.setManagerProductionCode(user.getProductionCode());
            managerRoute.setRoute(schedule);

            managerRoutes.add(managerRoute);
        }

        RegionalRouteResponseDTO response = new RegionalRouteResponseDTO();
        response.setRegionalCode(request.getRegionalInfo().getRegionalCode());
        response.setRoutes(managerRoutes);

        return response;
    }

    private List<CustomerModel> extractCustomers(UserDTO user) {

        List<CustomerModel> customers = new ArrayList<>();

        user.getSchedule().stream()
                .filter(s -> s.getCustomer() != null)
                .forEach(s -> {

                    CustomerModel model = new CustomerModel();
                    model.setName(s.getCustomer().getName());
                    model.setLat(s.getCustomer().getAddress().getCoordinates().getLat());
                    model.setLng(s.getCustomer().getAddress().getCoordinates().getLng());

                    // Simulação POC: 15 ou 30 minutos aleatório
                    model.setVisitDurationMinutes(
                            Math.random() > 0.5 ? 15 : 30
                    );

                    customers.add(model);
                });

        return customers;
    }
}
