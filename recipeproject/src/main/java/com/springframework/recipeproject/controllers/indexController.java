package com.springframework.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.recipeproject.service.RecipeService;

@Controller
public class indexController {
	private final RecipeService recipeService;

    public indexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
    	System.out.println(recipeService.getRecipe());
        model.addAttribute("recipes", recipeService.getRecipe());

        return "index";
    }

}
