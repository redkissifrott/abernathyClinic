package fr.redkissifrott.abernathyReport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.redkissifrott.abernathyReport")
public class AbernathyReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbernathyReportApplication.class, args);
	}

}
