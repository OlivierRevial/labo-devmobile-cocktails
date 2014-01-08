package com.ingesup.devmobile.labo.cocktails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.ingesup.devmobile.labo.cocktails.database.DatabaseManager;
import com.ingesup.devmobile.labo.cocktails.models.Cocktail;
import com.ingesup.devmobile.labo.cocktails.models.Ingredient;

public class MainActivity extends Activity implements OnClickListener {

	private LinearLayout llIngredients;
	private LinearLayout llCocktails;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DatabaseManager.getInstance().init(this);
        insertInDatabaseFirstTime();
        
        // Ici on récupère tous les boutons
        llIngredients = (LinearLayout) findViewById(R.id.llIngredients);
        llCocktails = (LinearLayout) findViewById(R.id.llCocktails);
     
        // La on enregistre le clic sur le bouton (et on définit leur action dans la méthode onClick() en dessous)
        llIngredients.setOnClickListener(this);
        llCocktails.setOnClickListener(this);
        
        // BASE DE DONNEES 
        // 1. On récupère la liste de tous les cocktails
        List<Cocktail> allCocktails = DatabaseManager.getInstance().getAllCocktails();
        // Ici on l'affiche avec une bête boucle for
        Log.d("TAG", "--------------- LISTE DES COCKTAILS ---------------------");
        for(int i=0; i<allCocktails.size(); i++) {
        	Log.d("TAG", allCocktails.get(i).toString());
        }
        
        // 2. On récupère la liste de tous les ingrédients
        List<Ingredient> allIngredients = DatabaseManager.getInstance().getAllIngredients();
        // Ici on l'affiche avec une bête boucle for
        Log.d("TAG", "--------------- LISTE DES INGREDIENTS ---------------------");
        for(int i=0; i<allIngredients.size(); i++) {
        	Log.d("TAG", allIngredients.get(i).toString());
        }
        
        // 3. On regarde si un ingrédient est dans le bar ou pas, et on le met à jour
        Ingredient anIngredient = allIngredients.get(0);
        // La on regarde si l'ingrédient est dans le bar...
        boolean isInBar = anIngredient.isInBar();
        Log.d("TAG", "L'ingrédient " + anIngredient.toString() + (isInBar ? " est dans le bar" : " n'est pas dans le bar"));
        // Ici on le change : si il était dans le bar on l'enlève du bar, sinon on l'y ajoute
        DatabaseManager.getInstance().updateIngredientInBar(anIngredient, !isInBar);
        // Et pour le test, on ré affiche l'ingrédient, normalement changé...
        isInBar = anIngredient.isInBar();
        Log.d("TAG", "L'ingrédient " + anIngredient.toString() + (isInBar ? " est dans le bar" : " n'est pas dans le bar"));
        // Pour finir, on remet l'ingrédient à sa valeur initiale vu que c'était juste un test...
        DatabaseManager.getInstance().updateIngredientInBar(anIngredient, !isInBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == llIngredients.getId()) {
			startActivity(new Intent(getApplicationContext(), MainActivityNicolas.class));
		}
		else if(arg0.getId() == llCocktails.getId()) {
			startActivity(new Intent(getApplicationContext(), MainActivityPierreAntoine.class));
		}
	}
    
	
	private void insertInDatabaseFirstTime() {
		if(!DatabaseManager.getInstance().hasAlreadyCocktails()) {
			List<Ingredient> ingredients = new ArrayList<Ingredient>();
			ingredients.add(new Ingredient("Benedictine"));
			ingredients.add(new Ingredient("Bourbon"));
			ingredients.add(new Ingredient("Vodka"));
			ingredients.add(new Ingredient("Boisson Energisante"));
			ingredients.add(new Ingredient("Bourbon"));
			ingredients.add(new Ingredient("Brandy"));
			ingredients.add(new Ingredient("Calvados"));
			ingredients.add(new Ingredient("Campari"));
			ingredients.add(new Ingredient("Liqueur de framboise"));
			ingredients.add(new Ingredient("Jus de pomme"));
			List<Cocktail> cocktails = new ArrayList<Cocktail>();
			cocktails.add(new Cocktail("Absolut raspberri", "La recette du absolut raspberri", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Adios motherfucker", "La recette de l'adios motherfucker", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Americano", "La recette du americano", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Apple Mojito", "La recette de l'apple mojito", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Appletini", "La recette de l'appletini", 1, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("B-52", "La recette du B-52", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Bahama mama", "La recette du bahama mama", 4, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Bahama Colada", "La recette du bahama colada", 3, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Bellini", "La recette du bellini", 5, getRandomIngredients(ingredients)));
			
			for(Cocktail cocktail : cocktails) {
				DatabaseManager.getInstance().insertCocktail(cocktail);
			} 
		}
	}
	
	private List<Ingredient> getRandomIngredients(List<Ingredient> ingredients) {
		Random rd = new Random();
		
		List<Ingredient> threeIngrs = new ArrayList<Ingredient>();
		while(threeIngrs.size() < 3) {
			Ingredient ingr = ingredients.get(rd.nextInt(ingredients.size()));
			if(!threeIngrs.contains(ingr)) {
				threeIngrs.add(ingr);
			}
		}
		return threeIngrs;
	}
}
