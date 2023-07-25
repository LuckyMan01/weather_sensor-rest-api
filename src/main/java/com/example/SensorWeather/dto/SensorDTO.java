package com.example.SensorWeather.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class SensorDTO {
    @NonNull
    private String name;
}
