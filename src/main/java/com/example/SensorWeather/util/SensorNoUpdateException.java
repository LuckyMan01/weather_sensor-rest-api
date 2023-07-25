package com.example.SensorWeather.util;

public class SensorNoUpdateException extends RuntimeException{
    public SensorNoUpdateException() {
    }

    public SensorNoUpdateException(String message) {
        super(message);
    }

    public SensorNoUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
