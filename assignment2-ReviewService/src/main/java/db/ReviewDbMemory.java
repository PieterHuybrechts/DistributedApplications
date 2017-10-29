package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.City;
import domain.Review;

public class ReviewDbMemory implements ReviewDb{

	private Map<Long,Review> db;
	private static ReviewDbMemory instance;
	private static int idCounter = 0;
	
	
	private ReviewDbMemory() {
		db = new HashMap<>();
	}
	
	public static synchronized ReviewDbMemory getInstance() {
		if(instance==null) {
			instance = new ReviewDbMemory();
		}
		
		return instance;
	}
	
	//------
	//Create
	//------
	
	public void add(Review review) {
		if(get(review.getId())==null) {
			review.setId(idCounter);
			idCounter++;
			db.put(review.getId(), review);
		}
	}
	
	//----
	//Read
	//----
	
	public List<Review> getAll(){
		return new ArrayList<>(db.values());
	}
	
	public Review get(long id){
		return db.get(id);
	}
	
	public List<Review> getAllFromCity(String zip){
		List<Review> reviews = new ArrayList<>();
		
		for(Review r : getAll()) {
			if(r.getZip().equals(zip)) {
				reviews.add(r);
			}
		}
		
		return reviews;
	}
	
	public List<Review> getAllFromCity(City city){
		List<Review> reviews = new ArrayList<>();
		
		for(Review r : getAll()) {
			if(r.getZip().equals(city)) {
				reviews.add(r);
			}
		}
		
		return reviews;
	}
	
	//------
	//Update
	//------
	
	public void update(Review review) {
		db.put(review.getId(), review);
	}
	
	//------
	//Delete
	//------
	
	public void remove(Review review) {
		db.remove(review.getId());
	}
	
	public void remove(Long id) {
		db.remove(id);
	}
	
}
