package dev.oltijanuzi.busride.repositories;

import dev.oltijanuzi.busride.entities.Bus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepo extends JpaRepository<Bus,Long> {
    List<Bus> findByAgencyAgencyNameContainingIgnoreCase(String name);
  
}
