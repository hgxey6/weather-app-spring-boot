package com.github.hgxey6.weather.controller;

import com.github.hgxey6.weather.model.Weather;
import com.github.hgxey6.weather.service.CurrentWeatherService;
import com.github.hgxey6.weather.service.WeatherDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private CurrentWeatherService currentWeatherService;
    private WeatherDataService weatherDataService;

    public WeatherController(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping("/current-weather")
    public Weather getCurrentWeather() {
        return currentWeatherService.getCurrentWeather();
    }

    @GetMapping("/weather")
    public List<Weather> getAll() {
        return weatherDataService.getAll();
    }
}
