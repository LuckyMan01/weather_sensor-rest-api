package com.example.SensorWeather.util;

import com.example.SensorWeather.model.Measurement;
import com.example.SensorWeather.service.MeasurementService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final MeasurementService service;

    public MeasurementValidator(MeasurementService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Measurement.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getValue() > 100 || measurement.getValue() < -100) {
            errors.rejectValue("value", "", "no correct enter is Temperature; Value should by between number -100 to 100");
        }
    }
}
