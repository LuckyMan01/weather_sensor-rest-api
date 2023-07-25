package com.example.SensorWeather.controller;

import com.example.SensorWeather.dto.SensorDTO;
import com.example.SensorWeather.model.Sensor;
import com.example.SensorWeather.service.SensorService;
import com.example.SensorWeather.util.SensorNoCreateException;
import com.example.SensorWeather.util.SensorNoUpdateException;
import com.example.SensorWeather.util.SensorValidator;
import jakarta.validation.Valid;
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
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService service;
    private final ModelMapper modelMapper;
    private final SensorValidator validator;


    @GetMapping
    public List<SensorDTO> findAll() {
        return service.findAll().stream()
                .map(this::convertToSensorDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public SensorDTO findById(Long id) {
        return convertToSensorDTO(service.FindById(id).get());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        Sensor sensor = convertToSensor(sensorDTO);
        validator.validate(sensor, bindingResult);
        FieldError fieldError = bindingResult.getFieldError();
        if (bindingResult.hasErrors()) {
            errorMsg.append("field:")
                    .append(fieldError.getField())
                    .append(" - ").append(fieldError.getDefaultMessage())
                    .append(";");

            throw new SensorNoCreateException(errorMsg.toString());
        }

        service.save(sensor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid SensorDTO sensorDTO, @RequestParam Long id, BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        if (bindingResult.hasErrors()) {
            for (FieldError error : fieldErrors) {
                errorMsg.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNoUpdateException(errorMsg.toString());
        }

        service.update(convertToSensor(sensorDTO), id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
