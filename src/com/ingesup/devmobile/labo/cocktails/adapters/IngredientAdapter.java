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
import com.ingesup.devmobile.labo.cocktails.models.Ingredient;

public class IngredientAdapter extends ArrayAdapter<Ingredient> implements OnClickListener {

	private int resource;
	private LayoutInflater inflater;
	
	public IngredientAdapter(Context context, int resource, List<Ingredient> ingredients) {
		super(context, resource, ingredients);
		inflater = LayoutInflater.from(context);
		this.resource = resource;
	}
	static class Holder
    {
        ImageView ImgIngredient;
        TextView NomIngredient;
    }
	boolean test = true;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		Ingredient saveIngredient = getItem(position);
		
        View row = inflater.inflate(resource, parent, false);
       
        holder = new Holder();
        holder.ImgIngredient = (ImageView)row.findViewById(R.id.ImgIngredient);
        holder.NomIngredient = (TextView)row.findViewById(R.id.NomIngredient);
           
		
		holder.NomIngredient.setText(saveIngredient.getNom());
        holder.ImgIngredient.setImageResource(saveIngredient.getImage());
		holder.NomIngredient.setOnClickListener(this);
        return row;
	}
		
	@Override
	public void onClick(View v) {
		TextView nomIngredient = (TextView) v;
		
		if (test) {
			nomIngredient.setTextColor(Color.RED);
			test = false;
		} else {
			nomIngredient.setTextColor(Color.GREEN);
			test = true;
		}
		
	}

	  
}
