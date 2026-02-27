package com.route.service.controller;


import com.route.service.dto.request.RouteRequestDTO;
import com.route.service.dto.response.RouteResponseDTO;
import com.route.service.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/optimize")
    public ResponseEntity<RouteResponseDTO> optimizeRoute(@RequestBody RouteRequestDTO request) {
        RouteResponseDTO response = routeService.calculateOptimizedRoute(request);
        return ResponseEntity.ok(response);
    }
}