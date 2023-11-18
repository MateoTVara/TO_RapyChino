package com.idat.pe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = DataSourceAutoConfiguration.class)
public class ToRapyChinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToRapyChinoApplication.class, args);
	}

}
