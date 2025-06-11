package dev.oltijanuzi.busride.services;

import dev.oltijanuzi.busride.dtos.ScheduleResponse;
import java.util.List;

public interface ScheduleService {
    List<ScheduleResponse> findSchedules(Long departureCityId, Long destinationCityId, Integer numberOfPassengers);
} 