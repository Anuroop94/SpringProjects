package com.springframework.jokesApp.jokes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class JokesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokesApplication.class, args);
		//JokeServiceImpl jsi = new JokeServiceImpl();
		//System.out.println(jsi.getJoke());
	}

}
