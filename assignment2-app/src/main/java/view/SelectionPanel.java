package view;

import javax.swing.JPanel;

import controller.ViewController;
import domain.City;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectionPanel extends JPanel {

	private JComboBox<City> comboBox;
	private ViewController viewController;
	
	public SelectionPanel() {
		setLayout(null);
		
		JLabel lblSelectCity = new JLabel("Select City");
		lblSelectCity.setBounds(10, 11, 67, 14);
		add(lblSelectCity);
		
		comboBox = new JComboBox();
		comboBox.setBounds(87, 8, 135, 20);
		add(comboBox);
		//comboBox.addActionListener(new ComboBoxListener());
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(232, 7, 89, 23);
		add(btnSelect);
		btnSelect.addActionListener(new ComboBoxListener());
	}
	
	public void setCities(City[] cities) {
		if(cities!=null) {
			for(int i=0;i<cities.length;i++) {
				comboBox.addItem(cities[i]);
			}			
		}
	}
	
	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
	
	private class ComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			City c = (City) comboBox.getSelectedItem();
			if(c!=null) {
				viewController.updateCityDetailPanel(c);				
			}
		}
		
	}
}
