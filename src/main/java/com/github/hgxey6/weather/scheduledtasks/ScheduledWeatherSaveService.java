package com.github.hgxey6.weather.scheduledtasks;

import com.github.hgxey6.weather.model.Weather;
import com.github.hgxey6.weather.service.CurrentWeatherService;
import com.github.hgxey6.weather.service.WeatherDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledWeatherSaveService {

    private final CurrentWeatherService currentWeatherService;
    private final WeatherDataService weatherDataService;

    public ScheduledWeatherSaveService(CurrentWeatherService currentWeatherService,
                                       WeatherDataService weatherDataService) {
        this.currentWeatherService = currentWeatherService;
        this.weatherDataService = weatherDataService;
    }

    @Scheduled(fixedRate=60*60*1000)
    public void getTheWeatherAndSave() {
        Weather weather = currentWeatherService.getCurrentWeather();
        weatherDataService.saveWeather(weather);
    }
}
