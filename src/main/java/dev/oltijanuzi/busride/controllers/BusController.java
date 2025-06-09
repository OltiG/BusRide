package dev.oltijanuzi.busride.controllers;

import dev.oltijanuzi.busride.dtos.BusDto;
import dev.oltijanuzi.busride.services.BusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    // Get all buses
    @GetMapping
    public ResponseEntity<List<BusDto>> getAllBuses() {
        List<BusDto> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    // Get bus by ID
    @GetMapping("/{id}")
    public ResponseEntity<BusDto> getBusById(@PathVariable Long id) {
        BusDto busDto = busService.getBusById(id);
        if (busDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(busDto);
    }

    // Create new bus
    @PostMapping
    public ResponseEntity<BusDto> createBus(@Valid @RequestBody BusDto busDto) {
        BusDto created = busService.saveBus(busDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update existing bus
    @PutMapping("/{id}")
    public ResponseEntity<BusDto> updateBus(@PathVariable Long id, @Valid @RequestBody BusDto busDto) {
        BusDto existing = busService.getBusById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        busDto.setId(id);
        BusDto updated = busService.saveBus(busDto);
        return ResponseEntity.ok(updated);
    }

    // Delete bus by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        BusDto existing = busService.getBusById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        busService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }
}

