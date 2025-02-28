package com.example.recipes_helper;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class RecipesHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesHelperApplication.class, args);
	}

	@GetMapping("/hello")
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
		return String.format("Hello, %s!", name);
	}

	
}
