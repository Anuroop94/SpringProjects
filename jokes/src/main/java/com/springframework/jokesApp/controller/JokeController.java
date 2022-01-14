package com.springframework.jokesApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.springframework.jokesApp.service.JokeService;

@Controller
public class JokeController {
	
	
	private final JokeService jokeService;
	

	public JokeController(JokeService jokeService) {
		this.jokeService = jokeService;
	}
	
	@RequestMapping({"","/","index","index.html"})
	public String showJoke(Model model) {
		//System.out.println(jokeService.getJoke());
		model.addAttribute("joke", "Hello World");
		return "index";
	}

}
