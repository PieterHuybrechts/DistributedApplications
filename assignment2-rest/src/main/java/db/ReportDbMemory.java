package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.City;
import domain.Report;

public class ReportDbMemory implements ReportDb{

	private Map<String ,Report> db;
	private static ReportDbMemory instance;
	
	private ReportDbMemory() {
		db = new HashMap<>();
	}
	
	public synchronized static ReportDbMemory getInstance() {
		if(instance == null) {
			instance = new ReportDbMemory();
		}
		return instance;
	}
	
	
	@Override
	public void add(City city, Report report) {
		add(city.getZip(),report);
	}
	
	@Override
	public void add(String zip, Report report) {
		db.put(zip, report);
	}

	@Override
	public Report get(String zip) {
		return db.get(zip);
	}

	@Override
	public Report get(City city) {
		return get(city.getZip());
	}

	@Override
	public List<Report> getAll() {
		return new ArrayList<>(db.values());
	}

	@Override
	public void update(City city, Report report) {
		add(city.getZip(),report);
	}

	@Override
	public void update(String zip, Report report) {
		db.put(zip, report);		
	}

}
