package com.ingesup.devmobile.labo.cocktails.models;

import java.util.HashMap;

import com.ingesup.devmobile.labo.cocktails.R;


public class CocktailImages {
	private HashMap<String, Integer> drawables;

	public CocktailImages() {
		super();

		drawables = new HashMap<String, Integer>();
		drawables.put("americano", R.drawable.cocktail_americano);
		drawables.put("apple_mojito", R.drawable.cocktail_apple_mojito);
		drawables.put("appletini", R.drawable.cocktail_appletini);
		drawables.put("b_52", R.drawable.cocktail_b_52);
		drawables.put("bahama_mama", R.drawable.cocktail_bahama_mama);
		drawables.put("bahama_colada", R.drawable.cocktail_banana_colada);
		drawables.put("bellini", R.drawable.cocktail_bellini);
		drawables.put("default", R.drawable.cocktail_default);
		drawables.put("adios_motherfucker", R.drawable.cocktail_adios_motherfucker);
		drawables.put("absolut_raspberri", R.drawable.cocktail_absolut_raspberri);
	}
	
	public int getCocktailDrawable(String cocktailName) {
		String aCocktailName = Utils.getNormalizedString(cocktailName);
		if(drawables.containsKey(aCocktailName)) {
			return drawables.get(aCocktailName);
		}
		return drawables.get("default");
	}
}
