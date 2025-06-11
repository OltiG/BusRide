package dev.oltijanuzi.busride.service.impl;

import dev.oltijanuzi.busride.dtos.ScheduleResponse;
import dev.oltijanuzi.busride.entity.Route;
import dev.oltijanuzi.busride.entity.Schedule;
import dev.oltijanuzi.busride.repository.RouteRepository;
import dev.oltijanuzi.busride.repository.ScheduleRepository;
import dev.oltijanuzi.busride.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final RouteRepository routeRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleResponse> findSchedules(String fromCity, String toCity, String date, Integer numberOfPassengers) {
        log.info("Finding schedules from {} to {} on {}", fromCity, toCity, date);
        
        List<Route> routes = routeRepository.findByDepartureAndDestination(fromCity, toCity);
        log.info("Found {} routes", routes.size());
        
        return routes.stream()
            .flatMap(route -> scheduleRepository.findActiveSchedulesByRoute(route.getId()).stream())
            .map(schedule -> mapToResponse(schedule, numberOfPassengers))
            .collect(Collectors.toList());
    }

    private ScheduleResponse mapToResponse(Schedule schedule, Integer numberOfPassengers) {
        ScheduleResponse response = new ScheduleResponse();
        response.setId(schedule.getId());
        response.setDepartureCity(schedule.getRoute().getDepartureCity().getName());
        response.setDestinationCity(schedule.getRoute().getDestinationCity().getName());
        response.setAgencyName(schedule.getAgency().getName());
        response.setDepartureTime(schedule.getDepartureTime());
        response.setArrivalTime(schedule.getArrivalTime());
        response.setBusNumber(schedule.getBusNumber());
        response.setAvailableSeats(schedule.getAvailableSeats());
        response.setPrice(schedule.getPrice());
        response.setTotalPrice(schedule.getPrice() * numberOfPassengers);
        return response;
    }
} 