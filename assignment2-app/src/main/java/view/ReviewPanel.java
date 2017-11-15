package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import controller.CityController;
import controller.ViewController;
import domain.City;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ReviewPanel extends JPanel {

	private JTextPane textPane;
	private ViewController viewController;
	private CityController cityController;
	private City city;
	
	public ReviewPanel() {
		setLayout(null);
		
		JLabel lblWriteReview = new JLabel("Write review");
		lblWriteReview.setBounds(0, 0, 112, 14);
		add(lblWriteReview);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 25, 430, 92);
		add(textPane);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(10, 128, 89, 23);
		add(btnSend);
		btnSend.addActionListener(new SendButtonListener());

	}
	
	private class SendButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(city!=null) {
				String text = textPane.getText();
				textPane.setText("");
				cityController.addReview(city,text);	
				viewController.updateCityDetailPanel(city);
			}	
		}
	}
	
	public void setCity(City c) {
		this.city = c;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	public void setCityController(CityController cityController) {
		this.cityController = cityController;
	}
}
