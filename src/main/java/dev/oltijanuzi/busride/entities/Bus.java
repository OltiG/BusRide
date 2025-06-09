package dev.oltijanuzi.busride.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String busBrand;
  
    @NotBlank
    private String licencePlate;
    

    @ManyToOne
    private Agency agency;
}
