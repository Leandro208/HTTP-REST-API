package com.pokemon.api.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.api.entity.Pokedex;
import com.pokemon.api.service.PokedexService;

@RestController
public class PokeController {

	@Autowired
	private PokedexService service;
	
	/*
	 * chamando metodo da class service, que retorna um objeto pokedex, com os 
	 * pokemons armazenados
	 */
	@GetMapping("/pokemons")
	public Pokedex getPokemons(@RequestParam(name = "name", required = false) String nome,
			@RequestParam(name = "sort", required = false) String ordenacao) {
		return service.getPokedex(nome, ordenacao);
	}
}
