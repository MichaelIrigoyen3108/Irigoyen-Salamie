package com.backend.Odontologo;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OdontologoApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(OdontologoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OdontologoApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
