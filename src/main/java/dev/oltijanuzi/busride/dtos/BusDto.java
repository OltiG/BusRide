package dev.oltijanuzi.busride.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusDto {
    private Long id;
    private String busBrand;
    private String licencePlate;
    private String agency;
}
