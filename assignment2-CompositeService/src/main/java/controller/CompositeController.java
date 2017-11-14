package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import domain.City;
import domain.Composite;
import domain.Report;
import domain.Review;

@RestController
public class CompositeController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(path="city/{zip}", method = RequestMethod.GET)
	public Composite get(@PathVariable String zip) {
		Composite composite = new Composite();

		//RestTemplate restTemplate = new RestTemplate();
		try {
			City city = restTemplate.getForObject("http://DestinationService/city/"+zip,City.class);			
			composite.setCity(city);
		}catch(Exception e) {
			composite.setCity(null);
		}
		
		try {
			Report report = restTemplate.getForObject("http://WeatherService/report/"+zip, Report.class);			
			composite.setReport(report);
		}catch (Exception e) {
			composite.setReport(null);
		}
		
		try {
			@SuppressWarnings("unchecked")
			List<Review> reviews = restTemplate.getForObject("http://ReviewService/review/"+zip,List.class);
			composite.setReviews(reviews);
		}catch (Exception e) {			
			composite.setReviews(null);
		}
		
		return composite;
	}
	
	@RequestMapping(path="/city",method=RequestMethod.GET)
	public City[] getAllCities(){
		return restTemplate.getForObject("http://DESTINATIONSERVICE/city", City[].class); 
	}
	
	@RequestMapping(path="/review", method = RequestMethod.POST)
	public void addReview(@RequestBody Review review) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Review> entity = new HttpEntity<>(review,headers);
		restTemplate.exchange("http://REVIEWSERVICE/review",HttpMethod.POST,entity,String.class);
	}
	
}
