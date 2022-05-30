package com.pokemon.api.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokemon.api.entity.Pokedex;
import com.pokemon.api.entity.Pokemon;
import com.pokemon.api.utils.NameComparator;

@Service
public class PokedexService {
	
	@Autowired
	private RestTemplate template; 
	
	public Pokedex getPokedex(String nome, String ordenacao) {
		/*
		 * metodo que retorna um objeto pokedex com os pokemons
		 */
		String url = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
		Pokedex objetoPokemon = template.getForObject(url, Pokedex.class);
		List<Pokemon> poke = objetoPokemon.getResults();
		/*
		 * se o parametro sort da url for igual a alf então ele vai ordenar a lista por
		 * ordem alfabetica
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
		// verificando se o campo nome tem algum valor armazenado
		if (nome != null && !nome.isEmpty()) {
			/*
			 * chamando metodo responsavel pela retirada de pokemons que nao tenham a ver
			 * com oque o usuario busscou e adicionando a tag <pre> no highlight do pokemon
			 */
			updateListPokemon(poke, nome);
		}
		// salvando lista com alterações no objeto Pokedex
		Pokedex resultado = new Pokedex();
		resultado.setResults(poke);
		return resultado;
	}
	
	private static void listaOrdenadaCaractere(List<Pokemon> lista) {
		/*
		 * Usando o algoritmo de ordenação bubble sort para ordenar pela quantidade de
		 * caractere
		 * 
		 * for que percorre a lista de pokemons e faz a troca de posição entre o menor e
		 * maior deixando ordenado
		 */
		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < lista.size(); j++) {
				if (lista.get(i).getName().length() < lista.get(j).getName().length()) {
					/*
					 * se o nome do objeto get(i) for menor que o nome do objeto get(j) então se faz
					 * a troca de posição
					 */
					Collections.swap(lista, i, j);
				}
			}
		}
	}

	private static void listaOrdemAlfabetica(List<Pokemon> lista) {
		/*
		 * Usando o algoritmo de ordenação bubble sort para colocar em ordem alfabetica
		 * 
		 * for que percorre a lista de pokemons e faz a troca de posição entre o menor e
		 * maior deixando ordenado
		 */
		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < lista.size(); j++) {
				NameComparator comp = new NameComparator();
				if (comp.compare(lista.get(i), lista.get(j)) < 0) {
					Collections.swap(lista, i, j);
				}
			}
		}
	}
	
	private static void updateListPokemon(List<Pokemon> poke, String nome) {
		/*
		 * AQUI JA COMEÇA A FILTRAGEM PARA DEIXAR SO OS POKEMONS RELACIONADOS COM A
		 * PESQUISA
		 * 
		 * eliminando objetos que tenham um nome menor que o texto buscado e tambem quem
		 * nao tem as inicias igual ao que foi buscado
		 */
		poke.removeIf(pokeObj -> pokeObj.getName().length() < nome.length());
		poke.removeIf(pokeObj -> !pokeObj.getName().substring(0, nome.length()).equalsIgnoreCase(nome));
		// percorrendo a lista de pokemons
		for (Pokemon p : poke) {
			String nomePokemon = p.getName();
			// verificando se o nome do pokemon é maior ou igual ao buscado
			if (nomePokemon.length() >= nome.length()) {
				/*
				 * armazenando o inicio do nome que é igual ao buscado e armazenando tbm a parte
				 * posterior do nome
				 */
				String finalTexto = nomePokemon.substring(nome.length(), nomePokemon.length());
				nomePokemon = nomePokemon.substring(0, nome.length());
				/*
				 * verificando se o nome digitado é igual ao nome do pokemon, ou se ele começa
				 * com o nome digitado
				 */
				if (nome.equalsIgnoreCase(nomePokemon)) {
					// adicionando <pre></pre> no texto digitado para ficar em destaque
					nomePokemon = "<pre>" + nome + "</pre>";
					// concatenando o inicio e o fim do nome
					p.setHighlight(nomePokemon + finalTexto);
				}
			}
		}
	}

}
