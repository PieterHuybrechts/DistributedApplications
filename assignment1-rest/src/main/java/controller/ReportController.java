package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import db.ReportDb;
import db.ReportDbMemory;
import domain.City;
import domain.Report;

@RestController
public class ReportController {
	
	private ReportDb reportDb;
	
	public ReportController() {
		reportDb = ReportDbMemory.getInstance();
	}
	
	public void saveReport(City city,Report report) {
		if(reportDb.get(city.getZip())!=null) {
			reportDb.update(city, report);
		}else {
			reportDb.add(city, report);
		}
	}
	
	public void saveReport(String zip,Report report) {
		if(reportDb.get(zip)!=null) {
			reportDb.update(zip, report);
		}else {
			reportDb.add(zip, report);
		}
	}
	
	@RequestMapping(path="/report",method = RequestMethod.GET)
	public Report get(@RequestParam(value="zip") String zip) {
		return reportDb.get(zip); 
	}
	
	@RequestMapping(path="/report",method = RequestMethod.POST)
	public void post(@RequestParam(value="zip") String zip,@RequestBody Report report) {
		saveReport(zip, report);
	}
	
	
}
