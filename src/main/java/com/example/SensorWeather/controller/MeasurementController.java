package com.example.SensorWeather.controller;

import com.example.SensorWeather.dto.MeasurementDTO;
import com.example.SensorWeather.model.Measurement;
import com.example.SensorWeather.service.MeasurementService;
import com.example.SensorWeather.service.SensorService;
import com.example.SensorWeather.util.MeasurementNoCreateException;
import com.example.SensorWeather.util.MeasurementValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final MeasurementValidator validator;

    @GetMapping
    public List<MeasurementDTO> findAll() {
        return measurementService.findAll().stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public String rainyDaysCount() {
        return measurementService.findAllRainyDays();
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody MeasurementDTO measurementDTO, BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);
        validator.validate(measurement, bindingResult);
        StringBuilder errorMsg = new StringBuilder();
        FieldError fieldErrors = bindingResult.getFieldError();

        if (bindingResult.hasErrors()) {
            errorMsg.append(fieldErrors.getField())
                    .append(" - ").append(fieldErrors.getDefaultMessage())
                    .append(";");

            throw new MeasurementNoCreateException(errorMsg.toString());
        }
        Long id = sensorService.findByName(measurementDTO.getSensor().getName())
                .get()
                .getId();

        measurement.setSensor(sensorService.FindById(id).get());
        measurementService.create(measurement);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
