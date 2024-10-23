package com.example.weather;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.weather.config.AlertConfig;
import com.example.weather.model.WeatherData;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private AlertConfig alertConfig;

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(alertConfig.getTemperatureThreshold()).thenReturn(35.0);
        when(alertConfig.getConsecutiveUpdates()).thenReturn(2);
    }

    @Test
    public void testTemperatureConversion() {
        assertEquals(0.0, kelvinToCelsius(273.15), 0.01);
        assertEquals(26.85, kelvinToCelsius(300.0), 0.01);
    }

    @Test
    public void testCheckAlerts_TriggerAlert() {
        WeatherData latestData = new WeatherData();
        latestData.setTemperature(36.0); // Exceeds threshold
        WeatherService spyService = spy(weatherService);
        boolean alertTriggered = spyService.checkAlerts(latestData);

        assertTrue(alertTriggered, "Alert should be triggered when temperature exceeds threshold");
        verify(spyService).triggerAlert(latestData); // Verify that triggerAlert was called
    }

    @Test
    public void testCheckAlerts_NoAlert() {
        WeatherData latestData = new WeatherData();
        latestData.setTemperature(34.0); // Below threshold

        weatherService.checkAlerts(latestData);
        boolean alertTriggered = weatherService.checkAlerts(latestData);

        assertFalse(alertTriggered, "Alert should not be triggered when temperature is below threshold");
    }
}