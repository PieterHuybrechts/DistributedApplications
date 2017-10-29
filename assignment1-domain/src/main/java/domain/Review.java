package domain;

public class Review {

	private long id;
	private String zip;
	private String review;
	
	public Review() {
		id=-1;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getZip() {
		return this.zip;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public String getReview() {
		return this.review;
	}
	
}
