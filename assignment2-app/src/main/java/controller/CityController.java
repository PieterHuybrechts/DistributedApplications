package controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import domain.City;
import domain.Composite;
import domain.Report;
import domain.Review;

public class CityController {

	public City[] getAllCities() {
		RestTemplate restTemplate = new RestTemplate();

		try {
			return restTemplate.getForObject("http://localhost:8765/composite/city", City[].class);
		} catch (ResourceAccessException e) {
			return null;
		}
	}

	public Composite getCityDetails(String zip) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			return restTemplate.getForObject("http://localhost:8765/composite/city/" + zip, Composite.class);
		} catch (Exception e) {
			return null;
		}
	}

	public Composite getCityDetails(City city) {
		return getCityDetails(city.getZip());
	}

	public void addReview(City city, String text) {
		RestTemplate restTemplate = new RestTemplate();
		Review r = new Review();
		r.setZip(city.getZip());
		r.setReview(text);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Review> entity = new HttpEntity<Review>(r,headers);
		
		try {
			restTemplate.exchange("http://localhost:8765/composite/review", HttpMethod.POST, entity,String.class);
		} catch (Exception e) {
			return;
		}
	}
}
