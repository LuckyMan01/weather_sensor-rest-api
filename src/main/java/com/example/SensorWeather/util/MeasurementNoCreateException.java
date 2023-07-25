package com.example.SensorWeather.util;

public class MeasurementNoCreateException extends RuntimeException{
    public MeasurementNoCreateException() {
    }

    public MeasurementNoCreateException(String message) {
        super(message);
    }

    public MeasurementNoCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
