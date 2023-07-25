package com.example.SensorWeather.service;

import com.example.SensorWeather.model.Measurement;
import com.example.SensorWeather.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Optional<Measurement> findById(Long id) {
        return measurementRepository.findById(id);
    }

    public String findAllRainyDays() {
        return measurementRepository.findAll().stream()
                .filter(m -> m.isRaining())
                .count() + "";
    }

    @Transactional
    public void create(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    @Transactional
    public void update(Measurement measurementUpdate, Long id) {
        measurementUpdate.setId(id);
        measurementRepository.save(measurementUpdate);
    }

    @Transactional
    public void delete(Long id) {
        measurementRepository.deleteById(id);
    }

}
