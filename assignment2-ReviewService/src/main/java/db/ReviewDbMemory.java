package db;

import java.util.ArrayList;
import java.util.List;

import domain.Review;

public class ReviewDbMemory implements ReviewDb{

	private List<Review> reviews;
	private static ReviewDbMemory instance;
	
	
	private ReviewDbMemory() {
		reviews = new ArrayList<>();
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
			reviews.add(review);
		}
	}
	
	//----
	//Read
	//----
	
	public List<Review> getAll(){
		return reviews;
	}
	
	public Review get(long id){
		Review r = null;
		
		for(Review temp:reviews) {
			if(temp.getId()==id) {
				r = temp;
				break;
			}
		}
		
		return r;
	}
	
	//------
	//Update
	//------
	
	public void update(long id,Review review) {
		
	}
	
	//------
	//Delete
	//------
	public void remove(Review review) {
		
	}
	
	public void remove(Long id) {
		
	}
	
}
