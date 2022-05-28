package com.pokemon.api.entity;

import java.util.List;

public class Pokedex {
	
	//lista com os pokemons 
	private List<Pokemon> results;
	
	private List<Pokemon> forms;

	public List<Pokemon> getForms() {
		return forms;
	}

	public void setForms(List<Pokemon> forms) {
		this.forms = forms;
	}

	public List<Pokemon> getResults() {
		return results;
	}

	public void setResults(List<Pokemon> results) {
		this.results = results;
	}

	
	
	
}
