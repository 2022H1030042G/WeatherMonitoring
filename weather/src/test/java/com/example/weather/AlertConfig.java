package com.example.weather;

import org.springframework.stereotype.Component;

@Component
public class AlertConfig {
    private double temperatureThreshold;

    public double getTemperatureThreshold() {
        return temperatureThreshold;
    }

    public void setTemperatureThreshold(double temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }
}