package app;

import javax.swing.JOptionPane;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import domain.Report;

public class Application {

	// private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		while (true) {
			String zip = JOptionPane.showInputDialog("Enter a zip code");
			if (zip==null || zip.isEmpty()) {
				break;
			} else {
				RestTemplate restTemplate = new RestTemplate();
				Report report = restTemplate.getForObject("http://localhost:8080/report?zip=" + zip, Report.class);

				String description = report.getWeather().get(0).getDescription();
				String name = report.getName();
				int t = (int) (report.getMain().getTemp() - 273.15) * 10;
				double temp = t / 10;

				String result = "The weather in " + name + " is:\n" + temp + " °C with " + description;

				JOptionPane.showMessageDialog(null, result);
			}

		}

	}

}
