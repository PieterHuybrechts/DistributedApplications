package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import domain.City;
import domain.Report;
import domain.Review;

@RestController
public class CompositeController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(path="/{zip}", method = RequestMethod.GET)
	public Map<String,Object> get(@PathVariable String zip) {
		Map<String,Object> temp = new HashMap<>();

		//RestTemplate restTemplate = new RestTemplate();
		try {
			City city = restTemplate.getForObject("http://DestinationService/city/"+zip,City.class);			
			temp.put("city", city);
		}catch(Exception e) {
			temp.put("city", "error");
		}
		
		try {
			Report report = restTemplate.getForObject("http://WeatherService/report/"+zip, Report.class);			
			temp.put("report", report);
		}catch (Exception e) {
			temp.put("report", "error");
		}
		
		try {
			@SuppressWarnings("unchecked")
			List<Review> reviews = restTemplate.getForObject("http://ReviewService/review/"+zip,List.class);
			temp.put("reviews", reviews);
		}catch (Exception e) {			
			temp.put("reviews", "error");
		}
		return temp;
	}
	
}