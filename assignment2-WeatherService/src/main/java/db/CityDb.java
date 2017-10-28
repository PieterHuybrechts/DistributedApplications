package db;

import java.util.List;

import domain.City;

public interface CityDb {

	//CREATE
	
	public void add(City city);
	
	//READ
	
	public City get(String zip);
	public List<City> getAll();
	
	//UPDATE
	
	//DELETE
	
	public void delete(String zip);
	
}
