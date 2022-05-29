package com.pokemon.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
	
	//factory para objetos template
	
	
	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
}
