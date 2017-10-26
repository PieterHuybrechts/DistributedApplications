package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import domain.City;
import domain.Report;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static int counter;
    
    public ScheduledTasks() {
    	counter = 40;
	}
    
    @Scheduled(fixedRate = 1100)
    public void getReport(){
    	City city = null;
    	
    	//--------------
    	//Get all cities
    	//--------------
    	
    	RestTemplate restTemplate = new RestTemplate();
    	City[] cities = restTemplate.getForObject("http://localhost:8080/city", City[].class);
    	
    	try {
    		city = cities[counter];
    	}catch(ArrayIndexOutOfBoundsException e) {
    		counter = 0;
    		city = cities[counter];
    	}
    	
    	try {
        	//-------------------
        	//Get report for city
        	//-------------------
    		
    		restTemplate = new RestTemplate();
    		Report report = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?zip="+city.getZip()+",be&appid=25bfba28ef618cf9bf295d556a389e69", Report.class);
    		log.info("Got report from City "+ counter +": "+ city);
    		
    		//-----------
    		//Post report
    		//-----------
    		
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		HttpEntity<Report> entity = new HttpEntity<Report>(report,headers);		
    		
    		restTemplate.exchange("http://localhost:8080/report?zip="+city.getZip(), HttpMethod.POST, entity,String.class);
    		
    	}catch(HttpClientErrorException e) {
    		log.error("Failed to get report for "+city);
    	}
    	
		
		counter++;
    }
}