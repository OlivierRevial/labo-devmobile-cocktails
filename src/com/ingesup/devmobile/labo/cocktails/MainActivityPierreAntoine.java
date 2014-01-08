package com.ingesup.devmobile.labo.cocktails;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ingesup.devmobile.labo.cocktails.adapters.CocktailAdaptater;
import com.ingesup.devmobile.labo.cocktails.database.DatabaseManager;
import com.ingesup.devmobile.labo.cocktails.models.Cocktail;

public class MainActivityPierreAntoine extends Activity implements OnItemClickListener {

	private ListView listView;
	private List<Cocktail> allCocktails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pierreantoine);
        allCocktails = DatabaseManager.getInstance().getAllCocktails();
        CocktailAdaptater adapter = new CocktailAdaptater(this, R.layout.cell_cocktail_adaptater, allCocktails); 
       
		listView = (ListView)findViewById(R.id.listCocktail); 
		listView.setAdapter(adapter); 
		listView.setOnItemClickListener(this);

   
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent = new Intent(getApplicationContext(), MainActivityElisa.class);
		
		intent.putExtra("idCocktail",allCocktails.get(position).getId()); 
		 intent.putExtra("nomCocktail",allCocktails.get(position).getNom()); 			  		  
		 intent.putExtra("noteCocktail",allCocktails.get(position).getNote());

		 startActivity(intent);  

	}
    

}




    

