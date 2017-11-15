package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CityController;
import controller.ViewController;
import domain.City;
import domain.Composite;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	
	private SelectionPanel selectionPanel;
	private DetailPanel detailPanel;
	private ReviewPanel reviewPanel; 
	private ViewController viewController;
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(new BorderLayout(0, 0));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {450, 0};
		gridBagLayout.rowHeights = new int[] {40, 300, 200, 30};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gridBagLayout);
		
		selectionPanel = new SelectionPanel();
		GridBagConstraints gbc_selectionPanel = new GridBagConstraints();
		gbc_selectionPanel.fill = GridBagConstraints.BOTH;
		gbc_selectionPanel.gridx = 0;
		gbc_selectionPanel.gridy = 0;
		contentPane.add(selectionPanel, gbc_selectionPanel);
		
		detailPanel = new DetailPanel();
		GridBagConstraints gbc_detailPanel = new GridBagConstraints();
		gbc_detailPanel.fill = GridBagConstraints.BOTH;
		gbc_detailPanel.gridx = 0;
		gbc_detailPanel.gridy = 1;
		contentPane.add(detailPanel, gbc_detailPanel);
		
		/*mainPanel = new MainPanel();
		contentPane.add(mainPanel);*/
		
		reviewPanel = new ReviewPanel();
		GridBagConstraints gbc_reviewPanel = new GridBagConstraints();
		gbc_reviewPanel.gridheight = 2;
		gbc_reviewPanel.fill = GridBagConstraints.BOTH;
		gbc_reviewPanel.gridx = 0;
		gbc_reviewPanel.gridy = 2;
		contentPane.add(reviewPanel, gbc_reviewPanel);
	}
	
	public void setCities(City[] cities) {
		selectionPanel.setCities(cities);
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
		selectionPanel.setViewController(viewController);
		reviewPanel.setViewController(viewController);
	}
	
	public void updateDetailPanel(Composite c) {
		detailPanel.updateDetails(c);
		reviewPanel.setCity(c.getCity());
	}

	public void setCityController(CityController cityController) {
		reviewPanel.setCityController(cityController);
		
	}

}
