package controller;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import domain.City;
import domain.Report;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static int counter;
    
    public ScheduledTasks() {
    	counter = 0;
	}
    
    @Scheduled(fixedRate = 1100)
    public void getReport(){
    	City city = null;
    	
    	//--------------
    	//Get all cities
    	//--------------
    	
    	RestTemplate restTemplate = new RestTemplate();
    	City[] cities = null;
    	try{
    		cities = restTemplate.getForObject("http://localhost:8765/rest/city", City[].class);    		
    	}catch(ResourceAccessException e) {
    		log.error("Could not fetch cities from Rest-server");
    		return;
    	}
    	
    	try {
    		city = cities[counter];
    	}catch(ArrayIndexOutOfBoundsException e) {
    		counter = 0;
    		city = cities[counter];
    	}
    	
    	//-------------------
    	//Get report for city
    	//-------------------
    	
    	restTemplate = new RestTemplate();
    	Report report = null;
    	
    	try {
    		report = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?zip="+city.getZip()+",be&appid=25bfba28ef618cf9bf295d556a389e69", Report.class);
    	}catch (HttpClientErrorException e) {
			if(e.getRawStatusCode()==404) {
				log.error("Failed to get report for "+city);
				return;
			}else {
				e.printStackTrace();
			}
		}catch (ResourceAccessException e) {
			if(e.contains(UnknownHostException.class)) {
				log.error("Could not get report; unknown host");
				return;
			}else if(e.contains(SocketException.class)){
				log.error("Could not get report");
				return;
			}else if(e.contains(SocketTimeoutException.class)) {
				log.error("Could not get report");
				return;
			}else {
				e.printStackTrace();
				return;
			}
		}
    	
    	//-----------
		//Post report
		//-----------
    	
    	try{    	
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		HttpEntity<Report> entity = new HttpEntity<Report>(report,headers);
    		restTemplate.exchange("http://localhost:8765/rest/report?zip="+city.getZip(), HttpMethod.POST, entity,String.class);
    	}catch(HttpClientErrorException e) {
    		if(e.getRawStatusCode() == 405 || e.getRawStatusCode() == 404 || e.getRawStatusCode() == 400) {
    			log.error("Failed to post report for "+city);
    			return;
    		}else {
    			e.printStackTrace();
    			return;
    		}
    	}
    	
    	log.info("Got and posted report from City "+ counter +": "+ city);	
		counter++;
    }
}