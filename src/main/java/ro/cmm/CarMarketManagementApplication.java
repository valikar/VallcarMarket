package ro.cmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ro.cmm"})
public class CarMarketManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarMarketManagementApplication.class, args);
	}
}
