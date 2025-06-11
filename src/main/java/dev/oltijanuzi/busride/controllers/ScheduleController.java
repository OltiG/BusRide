package dev.oltijanuzi.busride.controllers;

import dev.oltijanuzi.busride.dtos.ScheduleResponse;
import dev.oltijanuzi.busride.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/search")
    public ResponseEntity<List<ScheduleResponse>> searchSchedules(
            @RequestParam Long departureCityId,
            @RequestParam Long destinationCityId,
            @RequestParam(defaultValue = "1") Integer numberOfPassengers) {
        return ResponseEntity.ok(scheduleService.findSchedules(departureCityId, destinationCityId, numberOfPassengers));
    }
} 