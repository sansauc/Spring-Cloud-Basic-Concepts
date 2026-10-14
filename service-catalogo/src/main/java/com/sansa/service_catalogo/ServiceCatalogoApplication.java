package com.sansa.service_catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCatalogoApplication.class, args);
	}

}
