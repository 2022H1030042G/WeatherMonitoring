package com.example.weather;

import com.example.weather.config.AlertConfig;
import com.example.weather.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private AlertConfig alertConfig;

    public boolean checkAlerts(WeatherData weatherData) {
        if (weatherData.getTemperature() > alertConfig.getTemperatureThreshold()) {
            triggerAlert(weatherData);
            return true; // Alert triggered
        }
        return false; // No alert
    }

    private void triggerAlert(WeatherData weatherData) {
        // Logic to trigger an alert (e.g., send notification)
    }
}