package dev.oltijanuzi.busride.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSearchResponse {
    private String agencyName;
    private String busName;
    private String departureTime;
    private double ticketPrice;
    private int numberOfPeople;
    private double totalPrice;
}
