package com.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;


import com.weatherapp.model.Weather;



@Service
public class WeatherApiService {
	
	@Autowired
	private Environment env;
	
	/*
	 * This method call the External Open Weather Rest API using RestTemplate. 
	 */
	public Weather getCurrentWeather(String country_city) {
		
		
		String api_url=env.getProperty("WEATHER_API_URL");
		String api_key=env.getProperty("WEATHER_API_KEY");	
		/*
		 * Temperature is available in Fahrenheit, Celsius and Kelvin units.
		 * For temperature in Fahrenheit use units=imperial 
		 * For temperature in Celsius use  units=metric 
		 * Temperature in Kelvin is used by default, no need to use units 
		 * parameter in API call
		 */
		String api_metric=env.getProperty("WEATHER_API_UOM");
		
		String result = null;
		String url = api_url+"?units="+api_metric+"&q="+country_city+"&appid="+api_key;
		
		try {
			
		RestTemplate restTemplate = new RestTemplate();
		result = restTemplate.getForObject(url, String.class);
		System.out.println(result);        
        
		}catch (Exception e) {
            System.out.print("ERROR : "+e);
            return new Weather("Not Found","Not Found","Not Found","Not Found",0);
        }
		
		JSONObject root = new JSONObject(result);        
        //temperature-humidity-pressure
        JSONObject main = root.getJSONObject("main");
        System.out.println(main);
        //system
        JSONObject sys = root.getJSONObject("sys");
        //weather
        JSONArray wea = root.getJSONArray("weather");
        JSONObject weas = wea.getJSONObject(0);
        return new Weather(weas.getString("main"), weas.getString("description"), sys.getString("country"), root.getString("name"), main.getInt("temp") );
	}


	
	
	
	
		
		
		
		
	
	


}
