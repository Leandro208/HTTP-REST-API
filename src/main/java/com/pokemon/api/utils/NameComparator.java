package com.pokemon.api.utils;

import java.util.Comparator;

import com.pokemon.api.entity.Pokemon;

public class NameComparator implements Comparator<Pokemon> {

	
	@Override
	public int compare(Pokemon first, Pokemon second) {
		return first.getName().compareToIgnoreCase(second.getName());
	}
}
