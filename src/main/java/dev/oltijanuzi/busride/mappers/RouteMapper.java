package dev.oltijanuzi.busride.mappers;

import dev.oltijanuzi.busride.dtos.RouteDto;
import dev.oltijanuzi.busride.entities.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    uses = {BusMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RouteMapper {
    @Mapping(source = "bus.busBrand", target = "busName")
    @Mapping(source = "bus.agency.agencyName", target = "agencyName")
    RouteDto toDto(Route route);

    @Mapping(target = "bus", ignore = true)
    Route toEntity(RouteDto routeDto);
}
