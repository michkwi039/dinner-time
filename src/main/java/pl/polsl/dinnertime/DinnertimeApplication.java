package pl.polsl.dinnertime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DinnertimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinnertimeApplication.class, args);
	}

}
