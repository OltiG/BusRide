package dev.oltijanuzi.busride.services;

import dev.oltijanuzi.busride.dtos.BusDto;

import java.util.List;

public interface BusService {
    BusDto saveBus(BusDto busDto);
    List<BusDto> getAllBuses();
    BusDto getBusById(Long id);
    void deleteBus(Long id);
}
