package hu.kuerthl.runrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunRaceApplication {
	public enum Sex{male, female;}
	public static void main(String[] args) {
		SpringApplication.run(RunRaceApplication.class, args);
	}

}
