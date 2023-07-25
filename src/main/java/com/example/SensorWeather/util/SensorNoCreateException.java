package com.example.SensorWeather.util;

public class SensorNoCreateException extends RuntimeException {
    public SensorNoCreateException() {
    }

    public SensorNoCreateException(String message) {
        super(message);
    }

    public SensorNoCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
