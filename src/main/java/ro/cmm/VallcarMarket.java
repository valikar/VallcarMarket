package ro.cmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ro.cmm"})
public class VallcarMarket {

	public static void main(String[] args) {
		SpringApplication.run(VallcarMarket.class, args);
	}
}
