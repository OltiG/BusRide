package dev.oltijanuzi.busride.repository;

import dev.oltijanuzi.busride.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.departureCity.id = ?1 AND r.destinationCity.id = ?2")
    List<Route> findByDepartureAndDestination(Long departureCityId, Long destinationCityId);
} 