package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.City;

public class CityDbMemory implements CityDb {

	private Map<String, City> db;
	private static CityDbMemory instance;
	
	private CityDbMemory() {
		db = new HashMap<>();
	}
	
	public synchronized static CityDbMemory getInstance() {
		if(instance == null) {
			instance = new CityDbMemory();
		}
		
		return instance;
	}
	
	@Override
	public void add(City city) {
		
		
		db.put(city.getZip(), city);
	}

	@Override
	public City get(String zip) {
		return db.get(zip);
	}

	@Override
	public List<City> getAll() {
		return new ArrayList<>(db.values());
	}

	@Override
	public void delete(String zip) {
		db.remove(zip);
	}

}
