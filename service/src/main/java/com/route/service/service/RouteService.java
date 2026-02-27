package com.route.service.service;

import com.route.service.dto.request.RouteRequestDTO;
import com.route.service.dto.response.RouteResponseDTO;

public interface RouteService {
    RouteResponseDTO calculateOptimizedRoute(RouteRequestDTO request);
}
