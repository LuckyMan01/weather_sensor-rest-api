package com.example.SensorWeather.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor(force = true)
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "raining", nullable = false)
    private boolean isRaining;

    @NonNull
    @Min(-100)
    @Max(100)
    @Column(name = "temperature", nullable = false)
    private double value;

    @ManyToOne()
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;
}
