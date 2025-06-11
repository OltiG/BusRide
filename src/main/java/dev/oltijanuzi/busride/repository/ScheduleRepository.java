package dev.oltijanuzi.busride.repository;

import dev.oltijanuzi.busride.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE s.route.id = ?1 AND s.isActive = true ORDER BY s.departureTime")
    List<Schedule> findActiveSchedulesByRoute(Long routeId);
} 