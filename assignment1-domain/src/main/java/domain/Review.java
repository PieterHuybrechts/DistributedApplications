package domain;

public class Review {

	private long id;
	private City city;
	private String review;
	
	public Review() {
		
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public City getCity() {
		return this.city;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public String getReview() {
		return this.review;
	}
	
}
