package com.example.SensorWeather.service;

import com.example.SensorWeather.model.Sensor;
import com.example.SensorWeather.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }



    public Optional<Sensor> FindById(Long id) {
        return sensorRepository.findById(id);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Transactional
    public void update(Sensor updateSensor, Long id) {
        updateSensor.setId(id);
        sensorRepository.save(updateSensor);
    }

    @Transactional
    public void delete(Long id) {
        sensorRepository.deleteById(id);
    }

}
