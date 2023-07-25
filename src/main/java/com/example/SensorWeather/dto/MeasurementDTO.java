package com.example.SensorWeather.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(force = true)
public class MeasurementDTO {
    @NonNull
    private boolean isRaining;

    @NonNull
    @Min(-100)
    @Max(100)
    private double value;

    @NonNull
    private SensorDTO sensor;
}
