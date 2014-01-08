package com.ingesup.devmobile.labo.cocktails;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ingesup.devmobile.labo.cocktails.adapters.CocktailAdaptater.ViewHolder;
import com.ingesup.devmobile.labo.cocktails.database.DatabaseManager;
import com.ingesup.devmobile.labo.cocktails.models.Cocktail;
import com.ingesup.devmobile.labo.cocktails.models.Ingredient;

public class MainActivityElisa extends Activity implements OnClickListener {
	
	ImageButton favori;
	boolean isButtonOn = false;
	String nomCocktail;
	TextView nomCocktailTV;	
	ImageView imageCocktailIM;
	int idImage;
	TextView recetteCocktailTV;
	String recetteCocktail;
	TextView ingredientsCocktailTV;
	RatingBar noteCocktailRV;

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_elisa);

        int cocktailId = getIntent().getIntExtra("idCocktail", 0);
        
        List<Cocktail> allCocktails = DatabaseManager.getInstance().getAllCocktails();
        //recup d'un cocktail différent : ?!
         Cocktail cocktail = null;
         for (int x = 0 ; x <  allCocktails.size() ; x++)
         {
         	Cocktail monCocktail;
         	monCocktail = allCocktails.get(x);
         	if ( cocktailId == monCocktail.getId())
         	{
         		cocktail = monCocktail;
         		
         	}
         	
         }
         //recup de la note 
         int CocktailNote = cocktail.getNote();
         noteCocktailRV = (RatingBar)findViewById(R.id.ratingBar1);
         noteCocktailRV.setRating(CocktailNote);
         
        
        List<Ingredient> allIngredients = cocktail.getIngredients();
        
        
        favori = (ImageButton) findViewById(R.id.favori2);
        favori.setOnClickListener(this);
        
        nomCocktail = cocktail.getNom();
        nomCocktailTV = (TextView) findViewById(R.id.NomCocktail);
        nomCocktailTV.setText(nomCocktail);
       
        idImage = cocktail.getImage();	
        imageCocktailIM = (ImageView) findViewById(R.id.imageCocktail1);
        imageCocktailIM.setImageResource(idImage);
        
        recetteCocktail = cocktail.getRecette();
        recetteCocktailTV = (TextView)findViewById(R.id.textView4);
        recetteCocktailTV.setText(recetteCocktail);
        
        ingredientsCocktailTV = (TextView) findViewById(R.id.listIngredients);
    	String ingredientsCocktail = "";
        for (int x = 0 ; x <  allIngredients.size() ; x++)
        {
        	Ingredient monIngredient;
        	monIngredient = allIngredients.get(x);
        	ingredientsCocktail = ingredientsCocktail + monIngredient.getNom()+ "\n";        	
        	
        }
        ingredientsCocktailTV.setText(ingredientsCocktail);
        
        
    }

    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d("TAG", "Button clicked !");
		if(v.getId() == favori.getId())
		{
			if(isButtonOn)
			{
				favori.setImageResource(android.R.drawable.btn_star_big_off);
				isButtonOn = false;
			}
			else
			{
				favori.setImageResource(android.R.drawable.btn_star_big_on);
				isButtonOn = true;
			}
		}
		
	}
    
}
