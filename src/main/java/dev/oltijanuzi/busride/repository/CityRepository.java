package dev.oltijanuzi.busride.repository;

import dev.oltijanuzi.busride.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
} 