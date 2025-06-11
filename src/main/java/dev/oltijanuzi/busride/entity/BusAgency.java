package dev.oltijanuzi.busride.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bus_agencies")
public class BusAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;
} 