package com.ingesup.devmobile.labo.cocktails;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.ingesup.devmobile.labo.cocktails.adapters.IngredientAdapter;
import com.ingesup.devmobile.labo.cocktails.database.DatabaseManager;
import com.ingesup.devmobile.labo.cocktails.models.Ingredient;

public class MainActivityNicolas extends Activity {

	 ListView list;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_main_nicolas);
	 
		 list = (ListView)findViewById(R.id.IngredientListView);
		 List<Ingredient> allIngredients = DatabaseManager.getInstance().getAllIngredients();
		 IngredientAdapter adapter = new IngredientAdapter(this, R.layout.cellule_ingredient, allIngredients);
		 
		 	 
		 list.setAdapter(adapter);
			
		 
	 }
	 
}
        
