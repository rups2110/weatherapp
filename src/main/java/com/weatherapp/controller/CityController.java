package com.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.weatherapp.exception.ResourceNotFoundException;
import com.weatherapp.model.City;
import com.weatherapp.repository.CityRepository;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    CityRepository cityRepository;

    @GetMapping("/city")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    

    @GetMapping("/city/{id}")
    public City getCountryById(@PathVariable(value = "id") int cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", cityId));
    }
    
    @GetMapping("/citybycountry/{country_code}")
    public List<City> getCityByCountry_code(@PathVariable(value = "country_code") String countryCode) {
    	
    	System.out.println(countryCode);
    	return cityRepository.findByCountry_code(countryCode);
    	              
    }
    
    

    
    
}
