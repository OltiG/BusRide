package dev.oltijanuzi.busride.repositories;

import dev.oltijanuzi.busride.entities.Agency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepo extends JpaRepository<Agency,Long> {
    List<Agency> findByAgencyNameContainingIgnoreCase(String name);
  
}
