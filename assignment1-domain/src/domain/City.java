package domain;

public class City {


	private String zip;
	private String name;
	
	public City() {
		
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getZip() {
		return this.zip;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getZip()+" "+getName();
	}
	
}
