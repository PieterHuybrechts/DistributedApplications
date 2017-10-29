package db;

import java.util.List;

import domain.City;
import domain.Review;

public interface ReviewDb {

	//------
	//Create
	//------
	
	public void add(Review review);
	
	//----
	//Read
	//----
	
	public List<Review> getAll();
	public Review get(long id);
	public List<Review> getAllFromCity(String zip);
	public List<Review> getAllFromCity(City city);
	
	//------
	//Update
	//------
	
	public void update(Review review);
	
	//------
	//Delete
	//------
	public void remove(Review review);
	public void remove(Long id);
	
	
}
