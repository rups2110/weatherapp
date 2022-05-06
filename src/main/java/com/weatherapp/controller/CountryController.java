package com.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.weatherapp.exception.ResourceNotFoundException;
import com.weatherapp.model.Country;
import com.weatherapp.repository.CountryRepository;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/country")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    

    @GetMapping("/country/{id}")
    public Country getCountryById(@PathVariable(value = "id") int countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", countryId));
    }

    
    
}
