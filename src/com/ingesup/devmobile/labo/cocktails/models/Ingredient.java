package com.ingesup.devmobile.labo.cocktails.models;

<<<<<<< Updated upstream
import com.ingesup.devmobile.labo.cocktails.database.DatabaseManager;
=======
import android.widget.ArrayAdapter;
>>>>>>> Stashed changes


public class Ingredient {
	private int _id;
	private String nom;
	private int image;
	private boolean isInBar = false;
	
	public Ingredient() {
		
	}
	
	public Ingredient(String nom) {
		this.nom = nom;
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	
	public boolean isInBar() {
		isInBar = DatabaseManager.getInstance().isInBar(this);
		return isInBar;
	}
	
	public void setIsInBar(boolean isInBar) {
		this.isInBar = isInBar;
	}
	
	@Override
	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("Ingredient(ID:")
//			.append(_id)
//			.append("; Nom: ")
//			.append(nom)
//			.append(")");
//		return sb.toString();
		return nom;
	}
}
