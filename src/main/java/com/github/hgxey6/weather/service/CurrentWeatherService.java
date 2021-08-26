package com.github.hgxey6.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hgxey6.weather.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrentWeatherService {

    @Value("${weather.url}")
    private String URL;
    @Value("${weather.api}")
    private String API;
    @Value("${weather.city}")
    private String city;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CurrentWeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public Weather getCurrentWeather() {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("city", city);
        uriVariables.put("API", API);

        URI url = new UriTemplate(URL).expand(uriVariables);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return convert(response);
    }

    private Weather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            Weather weather = new Weather();
            weather.setDescription(root.path("weather").get(0).path("main").asText());
            weather.setTemperature(root.path("main").path("temp").asDouble());
            weather.setFeelsLike(root.path("main").path("feels_like").asDouble());
            weather.setWindSpeed(root.path("wind").path("speed").asDouble());
            weather.setPressure(root.path("main").path("pressure").asDouble());
            weather.setHumidity(root.path("main").path("humidity").asDouble());
            weather.setCityName(root.path("name").asText());
            weather.setTimeZone(root.path("timezone").toString());

            return weather;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
