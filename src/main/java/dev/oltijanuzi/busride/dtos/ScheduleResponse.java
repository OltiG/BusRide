package dev.oltijanuzi.busride.dtos;

import lombok.Data;
import java.time.LocalTime;

@Data
public class ScheduleResponse {
    private Long id;
    private String departureCity;
    private String destinationCity;
    private String agencyName;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String busNumber;
    private Integer availableSeats;
    private Double price;
    private Double totalPrice;
} 