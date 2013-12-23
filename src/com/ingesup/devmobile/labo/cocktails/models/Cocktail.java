package com.ingesup.devmobile.labo.cocktails.models;

import java.util.List;

public class Cocktail {
	private int _id;
	private String nom;
	private String recette;
	private int note;
	private int image;
	private List<Ingredient> ingredients;
	
	public Cocktail() {
		super();
	}
	
	public Cocktail(String nom, String recette, int note, List<Ingredient> ingredients) {
		this.nom = nom;
		this.recette = recette;
		this.note = note;
		this.ingredients = ingredients;
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
	public String getRecette() {
		return recette;
	}
	public void setRecette(String recette) {
		this.recette = recette;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cocktail(ID:")
			.append(_id)
			.append("; Nom: ")
			.append(nom)
			.append("; Note: ")
			.append(note)
			.append("; Recette: ")
			.append(recette)
			.append("; INGREDIENTS : ");
		
		for(Ingredient ingredient : ingredients) {
			sb.append(ingredient.toString()).append(" - ");
		}
		sb.append(")");
		return sb.toString();
	}
}
