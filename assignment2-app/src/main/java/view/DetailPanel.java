package view;

import javax.swing.JPanel;

import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;

import controller.ViewController;
import domain.Composite;
import domain.Review;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class DetailPanel extends JPanel {

	private JLabel temperature;
	private JLabel description;
	private JLabel reviewProgres;
	private JTextArea reviewTextArea;
	private int reviewCounter=0;
	
	private List<Review> reviews;
	
	
	public DetailPanel() {
		setLayout(null);
		
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setBounds(10, 11, 46, 14);
		add(lblDetails);
		
		JLabel lblTemp = new JLabel("Temp:");
		lblTemp.setBounds(20, 36, 46, 14);
		add(lblTemp);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(20, 61, 68, 14);
		add(lblDescription);
		
		temperature = new JLabel("");
		temperature.setBounds(93, 36, 98, 14);
		add(temperature);
		
		description = new JLabel("");
		description.setBounds(93, 61, 98, 14);
		add(description);
		
		reviewTextArea = new JTextArea();
		reviewTextArea.setBounds(10, 108, 430, 181);
		add(reviewTextArea);
		
		JLabel lblReviews = new JLabel("Reviews:");
		lblReviews.setBounds(10, 86, 61, 14);
		add(lblReviews);
		
		reviewProgres = new JLabel("0/0");
		reviewProgres.setBounds(81, 86, 80, 14);
		add(reviewProgres);
		
		JButton backButton = new JButton("<");
		backButton.setBounds(122, 82, 41, 23);
		add(backButton);
		backButton.addActionListener(new BackButtonListener());
		
		JButton nextButton = new JButton(">");
		nextButton.setBounds(171, 82, 41, 23);
		add(nextButton);
		nextButton.addActionListener(new NextButtonListener());

	}
	
	public void updateDetails(Composite c) {
		if(c==null) {
			JOptionPane.showMessageDialog(null, "Data could not be fetched");
			return;
		}
		
		if(c.getReport()!=null) {
			int t = (int) (c.getReport().getMain().getTemp() - 273.15) * 10;
			double temp = t / 10;
			temperature.setText(temp+"°C");
			description.setText(c.getReport().getWeather().get(0).getDescription());			
		}else {
			temperature.setText("Error");
			description.setText("Error");			
		}
		
		
		reviews = c.getReviews();
		
		if(reviews!=null && reviews.size()>0) {
				reviewProgres.setText("1/"+reviews.size());
				reviewTextArea.setText(reviews.get(0).getReview());
		}else{
			reviewProgres.setText("0/0");
			reviewTextArea.setText("");			
		}
	}
	
	private class NextButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(reviews!=null && reviewCounter+1<reviews.size()) {
				reviewCounter++;
				int temp = reviewCounter+1;
				reviewProgres.setText(temp+"/"+reviews.size());
				reviewTextArea.setText(reviews.get(reviewCounter).getReview());
			}
		}
	}
	
	private class BackButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(reviews!=null && reviewCounter>0) {
				reviewCounter--;			
				int temp = reviewCounter+1;
				reviewProgres.setText(temp+"/"+reviews.size());
				reviewTextArea.setText(reviews.get(reviewCounter).getReview());
			}
		}
	}
	
}
