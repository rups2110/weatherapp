package com.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherapp.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	

}





