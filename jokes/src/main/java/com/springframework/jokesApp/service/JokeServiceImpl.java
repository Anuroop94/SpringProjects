package com.springframework.jokesApp.service;

import org.springframework.stereotype.Service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;

@Service
public class JokeServiceImpl implements JokeService{
	
	private final ChuckNorrisQuotes chuckNorrisQuotes;
	
	public JokeServiceImpl() {
		this.chuckNorrisQuotes = new ChuckNorrisQuotes();
	}

	@Override
	public String getJoke() {
		// TODO Auto-generated method stub
		return chuckNorrisQuotes.getRandomQuote();
	}

}
