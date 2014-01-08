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
			ingredients.add(new Ingredient("Champagne"));
			ingredients.add(new Ingredient("Chocolat"));
			ingredients.add(new Ingredient("Cidre"));
			ingredients.add(new Ingredient("Cognac"));
			ingredients.add(new Ingredient("Cointreau"));
			ingredients.add(new Ingredient("Ananas"));
			ingredients.add(new Ingredient("Bananes"));
			ingredients.add(new Ingredient("Cola"));
			ingredients.add(new Ingredient("Gin"));
			ingredients.add(new Ingredient("Lait"));
			ingredients.add(new Ingredient("Soda"));
			List<Cocktail> cocktails = new ArrayList<Cocktail>();
			cocktails.add(new Cocktail("Absolut raspberri", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Adios motherfucker", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Americano", "Verser les alcools sur les glaçons, allonger à l'eau gazeuse, remuer et servir.", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Apple Mojito", " Placez le demi citron vert et la menthe fraîche dans le verre, ajouter la manzana, bien écraser à l'aide d'un pilon la préparation, ne pas ajouter de sucre, mettre le jus de pomme puis ajouter la glace pilée, ensuite allonger de perrier.", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Appletini", "Frapper au shaker avec des glaçons et servir en filtrant les glaçons.", 1, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("B-52", "Verser doucement à l'aide du dos d'une cuillère les ingrédients en commençant par la liqueur de café, suivie de la crème de whisky (bailey's) et enfin la liqueur d'orange (grand marnier de préférence), afin de les superposer par 3 couches distinctes ! Faire en sorte que le cointreau soit à ras du bord afin de le faire flamber librement. Laissez flamber 1 minute, plantez votre paille et aspirez tout le contenu d'un trait.", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("B-53", "Verser doucement à l'aide du dos d'une cuillère les ingrédients en commençant par la liqueur de café, suivie de la crème de whisky (bailey's) et enfin la liqueur d'orange (grand marnier de préférence), afin de les superposer par 3 couches distinctes ! Faire en sorte que le cointreau soit à ras du bord afin de le faire flamber librement. Laissez flamber 1 minute, plantez votre paille et aspirez tout le contenu d'un trait.", 3, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("B-54", "Verser doucement à l'aide du dos d'une cuillère les ingrédients en commençant par la liqueur de café, suivie de la crème de whisky (bailey's) et enfin la liqueur d'orange (grand marnier de préférence), afin de les superposer par 3 couches distinctes ! Faire en sorte que le cointreau soit à ras du bord afin de le faire flamber librement. Laissez flamber 1 minute, plantez votre paille et aspirez tout le contenu d'un trait.", 4, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Bloody Mary", "La recette du bloody Mary", 5, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Bronx", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 3, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Bahama Mama", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Banana Colada", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 3, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Bellini", "Utilisez de la purée de pêche ou écrasez une pêche jusqu'à obtenir une purée liquide. Ajoutez le sucre, mélangez et laissez ensuite la préparation au frigo environ 2 heures jusqu’à ce qu’elle ait la fraîcheur requise. Versez ensuite le nectar dans un verre puis compléter au champagne frais. Remuer en douceur et servir.", 4, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Chocolate Martini", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 5, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Cosmopolitan", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 1, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Cranberry Collins", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 3, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Cuba Libre", "Verser citron et rhum sur des glaçons. Compléter avec le coca cola. Remuer lentement.", 2, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Cucumber Cocktail", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 5, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Cucumber GT", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.", 1, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Daiquiri Glace", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.Le secret pour le réussir consiste à \"shaker\" vigoureusement, afin que la glace contenue dans le shaker refroidisse parfaitement la préparation, sans pour autant qu’elle ne commence à fondre. C’est tout un art.", 4, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Daiquiri glace à la mure", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.Le secret pour le réussir consiste à \"shaker\" vigoureusement, afin que la glace contenue dans le shaker refroidisse parfaitement la préparation, sans pour autant qu’elle ne commence à fondre. C’est tout un art.", 3, getRandomIngredients(ingredients)));
			cocktails.add(new Cocktail("Daiquiri glace à la framboise", "Frapper les ingrédients avec des glaçons et verser dans le verre en filtrant.Le secret pour le réussir consiste à \"shaker\" vigoureusement, afin que la glace contenue dans le shaker refroidisse parfaitement la préparation, sans pour autant qu’elle ne commence à fondre. C’est tout un art.", 5, getRandomIngredients(ingredients)));
			
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
