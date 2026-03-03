package com.route.service.controller;


import com.route.service.dto.request.RegionalRequestDTO;
import com.route.service.dto.request.RouteRequestDTO;
import com.route.service.dto.response.RegionalRouteResponseDTO;
import com.route.service.dto.response.RouteResponseDTO;
import com.route.service.service.RegionalRouteService;
import com.route.service.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final RegionalRouteService regionalRouteService;

    public RouteController(RouteService routeService, RegionalRouteService regionalRouteService) {
        this.routeService = routeService;
        this.regionalRouteService = regionalRouteService;
    }

    @PostMapping("/optimize")
    public ResponseEntity<RouteResponseDTO> optimizeRoute(@RequestBody RouteRequestDTO request) {
        RouteResponseDTO response = routeService.calculateOptimizedRoute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/regional")
    public ResponseEntity<RegionalRouteResponseDTO> generate(
            @RequestBody RegionalRequestDTO request) {

        return ResponseEntity.ok(
                regionalRouteService.generateRoutes(request)
        );
    }
}