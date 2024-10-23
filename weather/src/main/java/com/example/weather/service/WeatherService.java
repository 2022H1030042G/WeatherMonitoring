package com.example.weather.service;

import com.example.weather.config.AlertConfig;
import com.example.weather.model.DailyWeatherSummary;
import com.example.weather.model.WeatherData;
import com.example.weather.repository.DailyWeatherSummaryRepository;
import com.example.weather.repository.WeatherDataRepository;
import com.example.weather.util.WeatherDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    @Autowired
    private WeatherDataRepository repository;
    @Autowired
    private DailyWeatherSummaryRepository dailySummaryRepository;

    @Autowired
    private AlertConfig alertConfig;

    private double lastTemperature = Double.NaN;
    private int breachCount = 0;


    private final WeatherDataFetcher fetcher = new WeatherDataFetcher();

    @Scheduled(fixedRate = 300000) // 5 minutes
    public void fetchWeatherData() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
        for (String city : cities) {
            try {
                WeatherData data = fetcher.fetchWeatherData(city);
                repository.save(data);
                checkAlerts(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<WeatherData> getAllWeatherData() {
        return repository.findAll();
    }



    // Existing methods...

    public void calculateDailySummary() {
        List<WeatherData> allData = repository.findAll();
        // Group data by date
        Map<LocalDate, List<WeatherData>> groupedData = allData.stream()
                .collect(Collectors.groupingBy(data -> LocalDate.ofEpochDay(data.getTimestamp() / 86400)));

        for (Map.Entry<LocalDate, List<WeatherData>> entry : groupedData.entrySet()) {
            LocalDate date = entry.getKey();
            List<WeatherData> dailyData = entry.getValue();

            double avgTemp = dailyData.stream().mapToDouble(WeatherData::getTemperature).average().orElse(0);
            double maxTemp = dailyData.stream().mapToDouble(WeatherData::getTemperature).max().orElse(0);
            double minTemp = dailyData.stream().mapToDouble(WeatherData::getTemperature).min().orElse(0);
            String dominantCondition = getDominantCondition(dailyData);

            DailyWeatherSummary summary = new DailyWeatherSummary();
            summary.setDate(date);
            summary.setAverageTemperature(avgTemp);
            summary.setMaxTemperature(maxTemp);
            summary.setMinTemperature(minTemp);
            summary.setDominantCondition(dominantCondition);

            dailySummaryRepository.save(summary);
        }
    }

    private String getDominantCondition(List<WeatherData> dailyData) {
        Map<String, Long> conditionCount = dailyData.stream()
                .collect(Collectors.groupingBy(WeatherData::getCondition, Collectors.counting()));
        return conditionCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
    }

    public boolean checkAlerts(WeatherData latestData) {
        if (latestData.getTemperature() > alertConfig.getTemperatureThreshold()) {
            breachCount++;
            if (breachCount >= alertConfig.getConsecutiveUpdates()) {
                triggerAlert(latestData);
                return true;
            }
        } else {
            breachCount = 0; // Reset if below threshold
        }
        lastTemperature = latestData.getTemperature();
        return false;
    }

    public void triggerAlert(WeatherData latestData) {
        System.out.println("Alert: Temperature exceeded " + alertConfig.getTemperatureThreshold() + "°C for " + alertConfig.getConsecutiveUpdates() + " consecutive updates.");

    }
}