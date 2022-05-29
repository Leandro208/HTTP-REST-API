package com.pokemon.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokemon.api.entity.Pokedex;

@Service
public class PokedexService {
	
	@Autowired
	private RestTemplate template; 
	
	public Pokedex getPokedex() {
		/* metodo que retorna um objeto 
		 * pokedex com todos os pokemons
		 */
	
		String url = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
		
		return template.getForObject(url, Pokedex.class);
	}
	
}
