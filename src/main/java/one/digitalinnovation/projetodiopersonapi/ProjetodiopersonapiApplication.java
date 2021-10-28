package one.digitalinnovation.projetodiopersonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjetodiopersonapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetodiopersonapiApplication.class, args);
	}

}
