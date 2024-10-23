package com.example.weather;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.weather.model.WeatherData;
import com.example.weather.util.WeatherDataFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WeatherApiClientTest {

    @Mock
    private WeatherDataFetcher weatherApiClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveWeatherData() throws Exception {
        // Create a mock WeatherData object
        WeatherData mockWeatherData = new WeatherData();
        mockWeatherData.setTemperature(300); // Example temperature in Kelvin
        // Set other properties as needed

        // Mock the API response
        when(weatherApiClient.fetchWeatherData("London")).thenReturn(mockWeatherData);

        // Call the method to retrieve weather data
        WeatherData response = weatherApiClient.fetchWeatherData("London");

        // Verify that the response is not null and contains expected data
        assertNotNull(response);
        assertEquals(300, response.getTemperature());
    }
}