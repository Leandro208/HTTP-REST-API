package com.pokemon.api.entity;

public class Pokemon {
	
	//objeto que armazena os dados do pokemon
	
	private String name;
	
	private String highlight;
	
	
	
	public Pokemon() {
		super();
	}
	public Pokemon(String name, String highlight) {
		super();
		this.name = name;
		this.highlight = highlight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHighlight() {
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	
	
	
}
