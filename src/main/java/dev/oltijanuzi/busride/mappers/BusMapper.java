package dev.oltijanuzi.busride.mappers;

import dev.oltijanuzi.busride.dtos.BusDto;
import dev.oltijanuzi.busride.entities.Bus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BusMapper {
    @Mapping(source = "agency.agencyName", target = "agency")
    BusDto toDto(Bus bus);

    @Mapping(target = "agency", ignore = true)
    Bus toEntity(BusDto busDto);
}
