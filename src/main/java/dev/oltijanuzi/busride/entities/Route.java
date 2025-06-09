package dev.oltijanuzi.busride.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String departureCity;

    @NotBlank
    private String destinationCity;

    @Positive
    private double pricePerTicket;

    @NotNull
    private LocalTime departureTime;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    
}
