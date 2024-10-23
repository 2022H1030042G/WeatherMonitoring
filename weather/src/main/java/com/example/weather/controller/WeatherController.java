package com.example.weather.controller;

import com.example.weather.model.WeatherData;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService service;

    @GetMapping("/weather")
    public List<WeatherData> getAllWeatherData() {
        return service.getAllWeatherData();
    }

    @PostMapping("/calculate-daily-summary")
    public String calculateDailySummary() {
        try {
            service.calculateDailySummary();
            return "Daily summary calculated successfully.";
        } catch (Exception e) {
            return "Error calculating daily summary: " + e.getMessage();
        }
    }
}