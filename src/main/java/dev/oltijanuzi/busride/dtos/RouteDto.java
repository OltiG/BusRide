package dev.oltijanuzi.busride.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDto {
    private Long id;
    @NotBlank
    private String departureCity;
    @NotBlank
    private String destinationCity;

    private double pricePerTicket;
    @NotBlank
    private LocalTime departureTime;
    @NotBlank
    private String busName;
    @NotBlank
    private String agencyName;
}
