package com.pokemon.api.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.api.entity.Pokedex;
import com.pokemon.api.entity.Pokemon;
import com.pokemon.api.service.PokedexService;
import com.pokemon.api.utils.NameComparator;

@RestController
public class PokeController {

	@Autowired
	private PokedexService service;
	
	//metodo responsavel pela busca de todos os pokemons
	@GetMapping("pokedex")
	public Pokedex getPokedex(@RequestParam(name = "sort", required = false) String ordenacao) {
		/* chamando metodo que busca todos os pokemons
		 * e salvando em uma lista de Pokemon
		 */
		
		List<Pokemon> poke = service.getPokedex().getResults();
		/*se o parametro sort da url for igual a alf
		 * então ele vai ordenar a lista por ordem alfabetica
		 */
		if (ordenacao != null && !ordenacao.isEmpty()) {
			if (ordenacao.equalsIgnoreCase("alf")) {
				// ordem alfabetica
				listaOrdemAlfabetica(poke);
			}

			/*
			 * se o parametro sort da url for igual a comp então ele vai ordenar a lista
			 * pelo comprimento
			 */
			if (ordenacao.equalsIgnoreCase("comp")) {
				// ordenando por quantidade de caractere
				listaOrdenadaCaractere(poke);
			}
		}
		
		
		
		
		for (Pokemon p : poke) {
			p.setHighlight("<pre>" + p.getName() + "</pre>");
			}

		Pokedex resultado = new Pokedex();
		resultado.setResults(poke);
		return resultado;
	}
	
	
	//metodo responsavel pela busca de pokemons buscados
	@GetMapping("/pokemons")
	public Pokedex getPokemons(@RequestParam(name = "name", required = false) String nome,
							@RequestParam(name = "sort", required = false) String ordenacao) {
		
		/* chamando metodo que busca todos os 
		 * e salvando em uma lista de Pokemon
		 */
		List<Pokemon> poke = service.getPokedex().getResults();
		
		
		/*se o parametro sort da url for igual a alf
		 * então ele vai ordenar a lista por ordem alfabetica
		 */
		if (ordenacao != null && !ordenacao.isEmpty()) {
			if (ordenacao.equalsIgnoreCase("alf")) {
				// ordem alfabetica
				listaOrdemAlfabetica(poke);
			}

			/*
			 * se o parametro sort da url for igual a comp então ele vai ordenar a lista
			 * pelo comprimento
			 */
			if (ordenacao.equalsIgnoreCase("comp")) {
				// ordenando por quantidade de caractere
				listaOrdenadaCaractere(poke);
			}
		}
		
		/*AQUI JA COMEÇA A FILTRAGEM PARA DEIXAR SO OS POKEMONS
		 * RELACIONADOS COM A PESQUISA
		 */
		
		/*eliminando objetos que tenham um nome menor que o texto buscado
		 * e tambem quem nao tem as inicias igual ao que foi buscado
		 */
		poke.removeIf( pokeObj -> pokeObj.getName().length()<nome.length());
		poke.removeIf(pokeObj -> !pokeObj.getName()
				.substring(0, nome.length()).equalsIgnoreCase(nome));
		
		
		//percorrendo a lista de pokemons
		for (Pokemon p : poke) {
			
			String nomePokemon = p.getName();
			
			//verificando se o nome do pokemon é maior ou igual ao buscado
			if (nomePokemon.length() >= nome.length()) {
				nomePokemon = nomePokemon.substring(0, nome.length());
				
				
				/*verificando se o nome digitado é igual ao nome do pokemon, 
				 * ou se ele começa com o nome digitado
				 */
				if (nome.equalsIgnoreCase(nomePokemon)) {
					//adicionando <pre></pre> no texto digitado para ficar em destaque
					p.setHighlight(p.getName()
							.replace(nome, "<pre>" + nome + "</pre>"));
				} 	
			}	
		}
		
		//salvando lista com alterações no objeto Pokedex
		Pokedex resultado = new Pokedex();
		resultado.setResults(poke);
		return resultado;
	}
	
	
	public static void listaOrdenadaCaractere(List<Pokemon> lista){
		
		/* Usando o algoritmo de ordenação bubble sort 
		 * para ordenar pela quantidade de caractere
		 * 
		 * for que percorre a lista de pokemons e faz a troca de posição 
		 * entre o menor e maior deixando ordenado
		 */
		for(int i = 0; i<lista.size(); i++) {
			for(int j = 0; j<lista.size(); j++ ) {
				if(lista.get(i).getName().length()<lista.get(j).getName().length()) {
					/*se o nome do objeto get(i) for menor que o nome do objeto 
					 * get(j) então se faz a troca de posição
					 */
					Collections.swap(lista, i, j);
				}
			}
		}
	}
	
	
	public static void listaOrdemAlfabetica(List<Pokemon> lista){
		
		/* Usando o algoritmo de ordenação bubble sort 
		 * para colocar em ordem alfabetica
		 * 
		 * for que percorre a lista de pokemons e faz a troca de posição 
		 * entre o menor e maior deixando ordenado
		 */
		for(int i = 0; i<lista.size(); i++) {
			
			for(int j = 0; j<lista.size(); j++ ) {
				NameComparator comp = new NameComparator();
				
				if(comp.compare(lista.get(i), lista.get(j))<0) {
					Collections.swap(lista, i, j);
				}
			}
		}
		
		
		
		
	}
	
}
