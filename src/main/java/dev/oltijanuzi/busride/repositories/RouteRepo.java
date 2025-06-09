package dev.oltijanuzi.busride.repositories;

import dev.oltijanuzi.busride.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepo extends JpaRepository<Route,Long> {
    List<Route> findByDepartureCityAndDestinationCity(String departureCity, String destinationCity);

}
