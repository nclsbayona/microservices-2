package co.edu.javeriana.ms.Divider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DividerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DividerApplication.class, args);
	}

}