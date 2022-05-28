package com.pokemon.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.api.entity.Pokedex;
import com.pokemon.api.entity.Pokemon;
import com.pokemon.api.service.PokedexService;

@RestController
public class PokeController {

	@Autowired
	private PokedexService service;

	@GetMapping("pokedex")
	public Pokedex getPokedex() {
		/* chamando metodo que busca todos os pokemons
		 * e salvando em uma lista de Pokemon
		 */
		
		List<Pokemon> poke1 = service.getPokedex().getResults();

		for (int i = 0; i < poke1.size(); i++) {
			Pokemon pokemon = poke1.get(i);

			pokemon.setHighlight("<pre>" + pokemon.getName() + "</pre>");

		}

		Pokedex resultado = new Pokedex();
		resultado.setResults(poke1);
		return resultado;
	}

	@GetMapping("/pokemons")
	public Pokedex getPokemons(@RequestParam(name = "name", required = false) String nome) {
		
		/* chamando metodo que busca os pokemons pelo nome digitado na url
		 * e salvando em uma lista de Pokemon
		 */
		List<Pokemon> poke = service.getPokedex().getResults();
		
		poke.removeIf( pokeObj -> pokeObj.getName().length()<nome.length());
		poke.removeIf(pokeObj -> !pokeObj.getName()
				.substring(0, nome.length()).equalsIgnoreCase(nome));
		for (int i = 0; i < poke.size(); i++) {
			Pokemon pokemon = poke.get(i);
			String nomePokemon = pokemon.getName();
			
			
			if (nomePokemon.length() >= nome.length()) {
				nomePokemon = nomePokemon.substring(0, nome.length());

				if (nome.equalsIgnoreCase(nomePokemon)) {
					pokemon.setHighlight(pokemon.getName()
							.replace(nome, "<pre>" + nome + "</pre>"));
				} 
				
				
			}	
		}
		
		//salvando lista com alterações no objeto Pokedex
		Pokedex resultado = new Pokedex();
		resultado.setResults(poke);
		return resultado;
	}
	
}
