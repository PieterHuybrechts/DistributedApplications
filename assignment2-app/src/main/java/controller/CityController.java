package controller;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import domain.City;
import domain.Composite;

public class CityController {
	
	public City[] getAllCities() {
		RestTemplate restTemplate = new RestTemplate();
		
    	try{
    		return restTemplate.getForObject("http://localhost:8765/composite/city", City[].class);    		
    	}catch(ResourceAccessException e) {
    		return null;
    	}
	}
	
	public Composite getCityDetails(String zip) {
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			return restTemplate.getForObject("http://localhost:8765/composite/city/"+zip, Composite.class);			
		}catch(Exception e) {
			return null;
		}
	}
	
	public Composite getCityDetails(City city) {
		return getCityDetails(city.getZip());
	}
}
