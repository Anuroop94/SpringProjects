package com.springframework.recipeproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnitOfMeasure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	private String description;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
