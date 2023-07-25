package com.example.SensorWeather.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(min = 2, max = 30, message = "name must should not empty")
    @Column(name = "sensor_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurementList;

}
