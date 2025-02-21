package com.neha.springpro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "buses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bus number is required")
    @Column(unique = true)
    private String busNumber;

    @NotBlank(message = "Route is required")
    private String route;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    @NotBlank(message = "Driver name is required")
    private String driverName;
}
