package domain;

import java.util.List;

public class Composite {

	private Report report;
	private List<Review> reviews;
	private City city;
	
	public void setReport(Report report){
		this.report = report;
	}
	
	public Report getReport() {
		return this.report;
	}
	
	public void setReviews(List<Review> reviews) {
		this.reviews =  reviews;
	}
	
	public List<Review> getReviews(){
		return this.reviews;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public City getCity() {
		return this.city;
	}
	
	
}
