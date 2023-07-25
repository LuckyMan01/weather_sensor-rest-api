package com.example.SensorWeather.util;

public class SensorErrorResponse extends RuntimeException {
    public SensorErrorResponse() {
    }

    public SensorErrorResponse(String message) {
        super(message);
    }

    public SensorErrorResponse(Throwable cause) {
        super(cause);
    }

    public SensorErrorResponse(String message, Throwable cause) {
        super(message, cause);
    }
}
