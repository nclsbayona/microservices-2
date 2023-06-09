package co.edu.javeriana.ms.Substractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SubstractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubstractorApplication.class, args);
	}

}