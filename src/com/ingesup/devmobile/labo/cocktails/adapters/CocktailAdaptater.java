package com.ingesup.devmobile.labo.cocktails.adapters;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.ingesup.devmobile.labo.cocktails.R;
import com.ingesup.devmobile.labo.cocktails.models.Cocktail;


public class CocktailAdaptater extends ArrayAdapter<Cocktail> {
	
	private int resource;
	public CocktailAdaptater(Context context, int resource,
			 List<Cocktail> objects) {
		super(context, resource, objects);
		inflater = LayoutInflater.from(context); 
		this.resource = resource;
		this.bar = objects;
		
	}
	
	List<Cocktail> bar;

	LayoutInflater inflater;

	@Override
	public int getCount() {
		return bar.size();
	}
	
	@Override
	public Cocktail getItem(int position) {
		return bar.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView == null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(resource, null);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvnomCocktails = (TextView)convertView.findViewById(R.id.tvnomCocktails);
		holder.tvnoteCocktails = (TextView)convertView.findViewById(R.id.tvnoteCocktails);
		holder.tvimageCocktails = (ImageView)convertView.findViewById(R.id.tvimageCocktails);
			
		Cocktail monCocktail = getItem(position);
		String nomCocktail = monCocktail.getNom();
		int noteCocktail = monCocktail.getNote();
		int imageCocktail = monCocktail.getImage();

		holder.tvnomCocktails.setText(nomCocktail);
		holder.tvnoteCocktails.setText(String.valueOf(noteCocktail)+"/5");
		holder.tvimageCocktails.setImageResource(imageCocktail);
		
		return convertView;

	}
	
	
	public class ViewHolder {
		TextView tvnomCocktails;
		TextView tvnoteCocktails;
		ImageView tvimageCocktails;

	}

}
