package db;

import java.util.List;

import domain.City;
import domain.Report;

public interface ReportDb {

	//CREATE
	
	public void add(City city,Report report);
	public void add(String zip,Report report);
	
	//READ
	
	public Report get(String zip);
	public Report get(City city);
	public List<Report> getAll();
	
	//UPDATE
	
	public void update(City city,Report report);
	public void update(String zip,Report report);
	
	//DELETE
	
	
	
}
