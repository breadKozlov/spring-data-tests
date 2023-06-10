package by.kozlov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringDataApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplicationRunner.class, args);
	}

}
