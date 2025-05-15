package sopt.collaboration.alarmy.external.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.collaboration.alarmy.external.weather.service.WeatherService;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> weatherInfo(){
        return ResponseEntity.ok(weatherService.weather());
    }
}
