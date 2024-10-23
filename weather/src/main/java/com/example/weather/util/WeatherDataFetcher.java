package com.example.weather.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.example.weather.model.WeatherData;

import java.io.IOException;

public class WeatherDataFetcher {

    private static final String API_KEY = "e086fbab330b3f5253ad77928b4d86a2";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    private OkHttpClient client = new OkHttpClient();

    public WeatherData fetchWeatherData(String city) throws Exception {
        String url = String.format(BASE_URL, city, API_KEY);
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();

            // Check if the "main" object exists
            if (!json.has("main")) {
                throw new Exception("No main data found for city: " + city);
            }

            double temp = json.getAsJsonObject("main").get("temp").getAsDouble() - 273.15; // Convert to Celsius

            // Check if the "weather" array exists and has elements
            if (!json.has("weather") || json.getAsJsonArray("weather").size() == 0) {
                throw new Exception("No weather data found for city: " + city);
            }

            String condition = json.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
            long timestamp = json.get("dt").getAsLong();

            return new WeatherData(city, temp, condition, timestamp);
        }
    }
}