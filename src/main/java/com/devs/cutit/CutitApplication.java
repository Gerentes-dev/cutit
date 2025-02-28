package com.devs.cutit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.devs.cutit")
@SpringBootApplication
public class CutitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CutitApplication.class, args);
	}

}
