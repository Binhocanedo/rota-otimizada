package com.route.service.service;

import com.route.service.dto.ManagerRouteDTO;
import com.route.service.dto.RouteStopDTO;
import com.route.service.dto.ScheduleDTO;
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
    private final MatrixService matrixService;

    public RegionalRouteService(
            RouteOptimizationService routeOptimizationService,
            ScheduleSimulationService scheduleSimulationService,
            MatrixService matrixService) {

        this.routeOptimizationService = routeOptimizationService;
        this.scheduleSimulationService = scheduleSimulationService;
        this.matrixService = matrixService;
    }

    public RegionalRouteResponseDTO generateRoutes(RegionalRequestDTO request) {

        List<ManagerRouteDTO> routes = new ArrayList<>();

        for (UserDTO user : request.getUsers()) {

            List<CustomerModel> customers = extractCustomers(user);

            if (customers.isEmpty()) {
                continue;
            }

            double[][] durationMatrix = matrixService.buildDurationMatrix(customers);

            List<Integer> initialRoute =
                    routeOptimizationService.nearestNeighbor(durationMatrix);

            List<Integer> optimizedRoute =
                    routeOptimizationService.twoOpt(initialRoute, durationMatrix);

            List<RouteStopDTO> stops =
                    scheduleSimulationService.simulate(
                            customers,
                            optimizedRoute,
                            durationMatrix,
                            LocalTime.of(12, 0),
                            LocalTime.of(13, 0)
                    );

            ManagerRouteDTO managerRoute = new ManagerRouteDTO();
            managerRoute.setManagerProductionCode(user.getProductionCode());
            managerRoute.setRoute(stops);

            routes.add(managerRoute);
        }

        RegionalRouteResponseDTO response = new RegionalRouteResponseDTO();
        response.setRegionalCode(request.getRegionalInfo().getRegionalCode());
        response.setRoutes(routes);

        return response;
    }

    private List<CustomerModel> extractCustomers(UserDTO user) {

        List<CustomerModel> customers = new ArrayList<>();

        for (ScheduleDTO schedule : user.getSchedule()) {

            if ("CUSTOMER_VISIT".equals(schedule.getCategory())
                    && schedule.getCustomer() != null) {

                CustomerModel customer = new CustomerModel();

                customer.setName(schedule.getCustomer().getName());

                int duration =
                        (int) java.time.Duration.between(
                                schedule.getStartAt(),
                                schedule.getEndAt()
                        ).toMinutes();

                customer.setVisitDurationMinutes(duration);

                customer.setLat(
                        schedule.getCustomer().getAddress().getCoordinates().getLat());

                customer.setLng(
                        schedule.getCustomer().getAddress().getCoordinates().getLng());

                customers.add(customer);
            }
        }

        return customers;
    }
}
