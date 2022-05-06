package com.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import com.weatherapp.model.City;




@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	
		@Query(value = "SELECT * FROM cities where country_code=?1 ORDER BY RAND() LIMIT 10", nativeQuery = true) 
		List<City> findByCountry_code(String country_code);

}
