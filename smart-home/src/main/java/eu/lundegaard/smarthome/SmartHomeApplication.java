package eu.lundegaard.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
	}

}
