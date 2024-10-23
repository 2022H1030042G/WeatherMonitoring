package com.example.weather.repository;

import com.example.weather.model.DailyWeatherSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DailyWeatherSummaryRepository extends JpaRepository<DailyWeatherSummary, Long> {
    DailyWeatherSummary findByDate(LocalDate date);
}