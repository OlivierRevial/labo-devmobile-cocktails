package com.ingesup.devmobile.labo.cocktails.adapters;

import java.util.List;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingesup.devmobile.labo.cocktails.R;
import com.ingesup.devmobile.labo.cocktails.database.DatabaseManager;
import com.ingesup.devmobile.labo.cocktails.models.Ingredient;

@SuppressWarnings("unused")
public class IngredientAdapter extends ArrayAdapter<Ingredient> implements OnClickListener {

	private int resource;
	private LayoutInflater inflater;
	private List<Ingredient> ingredients;
	
	public IngredientAdapter(Context context, int resource, List<Ingredient> ingredients) {
		super(context, resource, ingredients);
		this.ingredients = ingredients;
		inflater = LayoutInflater.from(context);
		this.resource = resource;
	}
	static class Holder
    {
        ImageView ImgIngredient;
        TextView NomIngredient;
    }
	
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		Ingredient saveIngredient = getItem(position);
		boolean isInBar = saveIngredient.isInBar();
        View row = inflater.inflate(resource, parent, false);
        
        
        holder = new Holder();
        holder.ImgIngredient = (ImageView)row.findViewById(R.id.ImgIngredient);
        holder.NomIngredient = (TextView)row.findViewById(R.id.NomIngredient);
           
		
		holder.NomIngredient.setText(saveIngredient.getNom());
        holder.ImgIngredient.setImageResource(saveIngredient.getImage());
		holder.NomIngredient.setOnClickListener(this);
		
		if(!isInBar)
        {
        	holder.NomIngredient.setTextColor(Color.RED);
        }
        else
        {
        	holder.NomIngredient.setTextColor(Color.GREEN);
        }
        return row;
	
	}
	
	
	@Override
	public void onClick(View v) {
		TextView nomIngredient = (TextView) v;
		Ingredient ingredient = null;
		for(int i=0; i<ingredients.size(); i++) {
			if(ingredients.get(i).getNom().equals(nomIngredient.getText())) {
				ingredient = ingredients.get(i);
			}
		}
		
		if(ingredient != null) {
			boolean isInBar = ingredient.isInBar();
			if (isInBar) {
				nomIngredient.setTextColor(Color.RED);
			} else {
				nomIngredient.setTextColor(Color.GREEN);
			}
			DatabaseManager.getInstance().updateIngredientInBar(ingredient, !isInBar);
		}
	}
}
