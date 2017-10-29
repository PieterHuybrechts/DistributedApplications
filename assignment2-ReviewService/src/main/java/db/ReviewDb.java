package db;

import java.util.List;

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
	
	//------
	//Update
	//------
	
	public void update(long id,Review review);
	
	//------
	//Delete
	//------
	public void remove(Review review);
	public void remove(Long id);
	
	
}
