package com.example.SensorWeather.util;

import com.example.SensorWeather.model.Sensor;
import com.example.SensorWeather.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService service;

    @Autowired
    public SensorValidator(SensorService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (errors.hasErrors()){
            errors.rejectValue("name", "", "error enter sensoradww name. name must be between from 2 to 30 character ");
        }

        if (service.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is present; Enter another name");
        }
    }
}
