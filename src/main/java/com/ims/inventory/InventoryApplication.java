package com.ims.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class InventoryApplication extends SpringBootServletInitializer {

//	public static void main(String[] args) {
//		SpringApplication.run(InventoryApplication.class, args);
//	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InventoryApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InventoryApplication.class, args);
	}

}

