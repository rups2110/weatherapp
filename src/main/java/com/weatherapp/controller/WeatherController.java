package com.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.weatherapp.exception.ResourceNotFoundException;
import com.weatherapp.model.Country;
import com.weatherapp.model.Weather;
import com.weatherapp.repository.CountryRepository;
import com.weatherapp.service.WeatherApiService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    WeatherApiService weatherApiService;

   
    @GetMapping("/currentWeather/{country_city}")
    public Weather getCurrentWeatherForCity(@PathVariable(value = "country_city") String countryCity) {    	
    	   	
    	return weatherApiService.getCurrentWeather(countryCity);
    	
    }

    
    
}
