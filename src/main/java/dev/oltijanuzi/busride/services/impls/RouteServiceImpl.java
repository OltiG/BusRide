package dev.oltijanuzi.busride.services.impls;

import dev.oltijanuzi.busride.dtos.RouteDto;
import dev.oltijanuzi.busride.entities.Route;
import dev.oltijanuzi.busride.mappers.RouteMapper;
import dev.oltijanuzi.busride.repositories.RouteRepo;
import dev.oltijanuzi.busride.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepo routeRepo;
    private final RouteMapper routeMapper;

    @Override
    public RouteDto saveRoute(RouteDto routeDto) {
        Route route = routeMapper.toEntity(routeDto);
        return routeMapper.toDto(routeRepo.save(route));
    }

    @Override
    public List<RouteDto> getAllRoutes() {
        return routeRepo.findAll()
                .stream()
                .map(routeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDto getRouteById(Long id) {
        return routeRepo.findById(id)
                .map(routeMapper::toDto)
                .orElse(null);
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepo.deleteById(id);
    }
}
