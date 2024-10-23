package com.example.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alert")
public class AlertConfig {
    private double temperatureThreshold;
    private int consecutiveUpdates;

    // Getters and Setters
    public double getTemperatureThreshold() {
        return temperatureThreshold;
    }

    public void setTemperatureThreshold(double temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }

    public int getConsecutiveUpdates() {
        return consecutiveUpdates;
    }

    public void setConsecutiveUpdates(int consecutiveUpdates) {
        this.consecutiveUpdates = consecutiveUpdates;
    }
}
