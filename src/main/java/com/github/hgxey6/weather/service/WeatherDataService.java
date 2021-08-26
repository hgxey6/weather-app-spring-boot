package com.github.hgxey6.weather.service;

import com.github.hgxey6.weather.model.Weather;
import com.github.hgxey6.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherDataService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void saveWeather(Weather weather) {
        weatherRepository.save(weather);
    }

    public List<Weather> getAll() {
        return weatherRepository.findAll();
    }
}
