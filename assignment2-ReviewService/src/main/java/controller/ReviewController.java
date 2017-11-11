package controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import db.ReviewDb;
import db.ReviewDbMemory;
import domain.Review;

@RestController
public class ReviewController {

	private ReviewDb reviewDb;
	
	public ReviewController() {
		reviewDb = ReviewDbMemory.getInstance();
		addTestData();
	}
	
	private void addTestData() {
		
		Review r1 = new Review();
		r1.setZip("1000");
		r1.setReview("TestReview");
		
		saveReview(r1);

		Review r2 = new Review();
		r2.setZip("3000");
		r2.setReview("TestReview2");
		
		saveReview(r2);
	}
	
	
	private void saveReview(Review review) {
		if(review.getId()<0) {
			reviewDb.add(review);
		}else {
			reviewDb.update(review);
		}
	}
	
	@RequestMapping(path="/review",method=RequestMethod.GET)
	public List<Review> getAll() {
		return reviewDb.getAll();
	}
	
	@RequestMapping(path="/review/{zip}",method=RequestMethod.GET)
	public List<Review> get(@PathVariable String zip) {
		return reviewDb.getAllFromCity(zip);
	}
	
	@RequestMapping(path="/review",method=RequestMethod.POST)
	public void post(@RequestBody Review review){
		saveReview(review);
	}
	
}
