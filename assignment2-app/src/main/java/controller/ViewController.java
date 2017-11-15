package controller;

import domain.City;
import domain.Composite;
import view.MainWindow;

public class ViewController {

	private CityController cityController;
	private MainWindow mainWindow;
	
	public ViewController() {
		this.cityController = new CityController();
		this.mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		
		setCitySelectionPanel();
		mainWindow.setViewController(this);
		mainWindow.setCityController(cityController);
	}
	
	public void setCitySelectionPanel() {
		mainWindow.setCities(cityController.getAllCities());
	}
	
	public void updateCityDetailPanel(String zip){
		Composite c = cityController.getCityDetails(zip);
		this.updateCityDetailPanel(c);
	}
	
	public void updateCityDetailPanel(City city) {
		Composite c = cityController.getCityDetails(city);
		this.updateCityDetailPanel(c);
	}
	
	public void updateCityDetailPanel(Composite c) {
		mainWindow.updateDetailPanel(c);
	}
	
}
