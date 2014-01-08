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
		drawables.put("banana_colada", R.drawable.cocktail_banana_colada);
		drawables.put("bellini", R.drawable.cocktail_bellini);
		drawables.put("default", R.drawable.cocktail_default);
		drawables.put("adios_motherfucker", R.drawable.cocktail_adios_motherfucker);
		drawables.put("absolut_raspberri", R.drawable.cocktail_absolut_raspberri);
		drawables.put("b_53", R.drawable.cocktail_b_53);
		drawables.put("b_54", R.drawable.cocktail_b_54);
		drawables.put("bloody_mary", R.drawable.cocktail_bloody_mary);
		drawables.put("bahama_mama", R.drawable.cocktail_bahama_mama);
		drawables.put("bellini", R.drawable.cocktail_bellini);
		drawables.put("bronx", R.drawable.cocktail_bronx);
		drawables.put("chocolate_martini", R.drawable.cocktail_chocolate_martini);
		drawables.put("cosmopolitan", R.drawable.cocktail_cosmopolitan);
		drawables.put("cranberry_collins", R.drawable.cocktail_cranberry_collins);
		drawables.put("cuba_libre", R.drawable.cocktail_cuba_libre);
		drawables.put("cucumber_cocktail", R.drawable.cocktail_cucumber_cocktail);
		drawables.put("cucumber_gt", R.drawable.cocktail_cucumber_gt);
		drawables.put("daiquiri_glace_a_la_framboise", R.drawable.cocktail_daiquiri_glace_a_la_framboise);
		drawables.put("daiquiri_glace_a_la_mure", R.drawable.cocktail_daiquiri_glace_a_la_mure);
		drawables.put("daiquiri_glace", R.drawable.cocktail_daiquiri_glace);
	}
	
	public int getCocktailDrawable(String cocktailName) {
		String aCocktailName = Utils.getNormalizedString(cocktailName);
		if(drawables.containsKey(aCocktailName)) {
			return drawables.get(aCocktailName);
		}
		return drawables.get("default");
	}
}
