package dev.oltijanuzi.busride.controllers;

import dev.oltijanuzi.busride.dtos.RouteDto;
import dev.oltijanuzi.busride.services.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    // Get all routes
    @GetMapping
    public ResponseEntity<List<RouteDto>> getAllRoutes() {
        List<RouteDto> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    // Get route by ID
    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
        RouteDto routeDto = routeService.getRouteById(id);
        if (routeDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(routeDto);
    }

    // Create new route
    @PostMapping
    public ResponseEntity<RouteDto> createRoute(@Valid @RequestBody RouteDto routeDto) {
        RouteDto created = routeService.saveRoute(routeDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update existing route
    @PutMapping("/{id}")
    public ResponseEntity<RouteDto> updateRoute(@PathVariable Long id, @Valid @RequestBody RouteDto routeDto) {
        RouteDto existing = routeService.getRouteById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        routeDto.setId(id);
        RouteDto updated = routeService.saveRoute(routeDto);
        return ResponseEntity.ok(updated);
    }

    // Delete route by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        RouteDto existing = routeService.getRouteById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
