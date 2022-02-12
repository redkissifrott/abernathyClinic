package fr.redkissifrott.abernathyClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.redkissifrott.abernathyClient")
public class AbernathyClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbernathyClientApplication.class, args);
	}

}
