package com.example.weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DailyWeatherSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double averageTemperature;
    private double maxTemperature;
    private double minTemperature;
    private String dominantCondition;

    private List<Double> temperatures = new ArrayList<>();

    public void addTemperature(double temperature) {
        temperatures.add(temperature);
        calculateSummary();
    }

    private void calculateSummary() {
        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (double temperature : temperatures) {
            sum += temperature;
            if (temperature > max) {
                max = temperature;
            }
            if (temperature < min) {
                min = temperature;
            }
        }

        averageTemperature = sum / temperatures.size();
        maxTemperature = max;
        minTemperature = min;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getDominantCondition() {
        return dominantCondition;
    }

    public void setDominantCondition(String dominantCondition) {
        this.dominantCondition = dominantCondition;
    }

    // Getters and Setters
}