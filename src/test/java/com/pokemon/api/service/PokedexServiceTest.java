package com.pokemon.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.pokemon.api.entity.Pokedex;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
public class PokedexServiceTest {
	
	@Autowired
	private PokedexService service;
	
	@Test
	public void getPokedexTest() {
		Pokedex pokemons = service.getPokedex("di", null);
		
		assertEquals("diglett", pokemons.getResults().get(0).getName());
		assertEquals("<pre>di</pre>glett", pokemons.getResults().get(0).getHighlight());
		
		assertEquals("ditto", pokemons.getResults().get(1).getName());
		assertEquals("<pre>di</pre>tto", pokemons.getResults().get(1).getHighlight());
		
		assertEquals("dialga", pokemons.getResults().get(2).getName());
		assertEquals("<pre>di</pre>alga", pokemons.getResults().get(2).getHighlight());
	}
}
