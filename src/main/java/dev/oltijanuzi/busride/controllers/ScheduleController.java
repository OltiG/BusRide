package dev.oltijanuzi.busride.controllers;

import dev.oltijanuzi.busride.dtos.ScheduleResponse;
import dev.oltijanuzi.busride.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/search")
    public ResponseEntity<List<ScheduleResponse>> searchSchedules(
            @RequestParam String fromCity,
            @RequestParam String toCity,
            @RequestParam String date,
            @RequestParam(defaultValue = "1") Integer numberOfPassengers) {
        log.info("Searching schedules from {} to {} on {}", fromCity, toCity, date);
        return ResponseEntity.ok(scheduleService.findSchedules(fromCity, toCity, date, numberOfPassengers));
    }
} 