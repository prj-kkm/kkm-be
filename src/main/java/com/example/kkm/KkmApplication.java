package com.example.kkm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@SpringBootApplication
public class KkmApplication {

	public static void main(String[] args) {
		SpringApplication.run(KkmApplication.class, args);
	}

}
