package com.example.weather;

import static org.junit.jupiter.api.Assertions.*;

import com.example.weather.model.DailyWeatherSummary;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DailyWeatherSummaryTest {

    @Test
    public void testCalculateDailySummary() {
        DailyWeatherSummary summary = new DailyWeatherSummary();
        summary.addTemperature(30);
        summary.addTemperature(32);
        summary.addTemperature(28);

        assertEquals(30.0, summary.getAverageTemperature());
        assertEquals(32.0, summary.getMaxTemperature());
        assertEquals(28.0, summary.getMinTemperature());
        assertEquals("clear sky", summary.getDominantCondition());
    }
}