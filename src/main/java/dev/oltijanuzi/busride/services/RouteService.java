package dev.oltijanuzi.busride.services;

import dev.oltijanuzi.busride.dtos.RouteDto;

import java.util.List;

public interface RouteService {
    RouteDto saveRoute(RouteDto route);
    List<RouteDto> getAllRoutes();
    RouteDto getRouteById(Long id);
    void deleteRoute(Long id);
}
