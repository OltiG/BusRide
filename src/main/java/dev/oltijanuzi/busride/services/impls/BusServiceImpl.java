package dev.oltijanuzi.busride.services.impls;

import dev.oltijanuzi.busride.dtos.BusDto;
import dev.oltijanuzi.busride.entities.Bus;
import dev.oltijanuzi.busride.mappers.BusMapper;
import dev.oltijanuzi.busride.repositories.BusRepo;
import dev.oltijanuzi.busride.services.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
    private final BusRepo busRepo;
    private final BusMapper busMapper;

    @Override
    public BusDto saveBus(BusDto busDto) {
        Bus bus = busMapper.toEntity(busDto);
        return busMapper.toDto(busRepo.save(bus));
    }

    @Override
    public List<BusDto> getAllBuses() {
        return busRepo.findAll()
                .stream()
                .map(busMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BusDto getBusById(Long id) {
        return busRepo.findById(id)
                .map(busMapper::toDto)
                .orElse(null);
    }

    @Override
    public void deleteBus(Long id) {
        busRepo.deleteById(id);
    }
}
