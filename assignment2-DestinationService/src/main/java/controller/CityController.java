package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import db.CityDb;
import db.CityDbMemory;
import domain.City;

@RestController
public class CityController {

	private CityDb db;
	
	public CityController() {
		db = CityDbMemory.getInstance();
		fillDb();
	}
	
	public void fillDb() {
		String fileName = "src/main/resources/zipcodes.txt";
		File file = new File(fileName);		
		BufferedReader in = null;
		String line;
		
		try {
			in = new BufferedReader(new FileReader(file));
			while((line = in.readLine())!=null) {
				String[] props = line.split(";");
				City city = new City();
				city.setZip(props[0]);
				city.setName(props[1]);
				db.add(city);
			}
			in.close();
		}catch(FileNotFoundException e) {
			
		}catch (IOException e) {
			
		}finally {
			try {in.close();} catch (IOException e) {}
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<City> getAll(){
		return db.getAll();
	}
	
	@RequestMapping(path="/{zip}")
	public City get(@PathVariable String zip) {
		return db.get(zip);
	}
}
