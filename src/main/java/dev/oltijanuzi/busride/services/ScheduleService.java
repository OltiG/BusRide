package dev.oltijanuzi.busride.services;

import dev.oltijanuzi.busride.dtos.ScheduleResponse;
import java.util.List;

public interface ScheduleService {
    List<ScheduleResponse> findSchedules(String fromCity, String toCity, String date, Integer numberOfPassengers);
} 