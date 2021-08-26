package com.github.hgxey6.weather.controller;

import com.github.hgxey6.weather.model.Weather;
import com.github.hgxey6.weather.service.CurrentWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private CurrentWeatherService currentWeatherService;

    public WeatherController(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping("/current-weather")
    public Weather getCurrentWeather() {
        Weather weather = currentWeatherService.getCurrentWeather();
        return weather;
    }
}
