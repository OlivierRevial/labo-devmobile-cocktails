package com.ingesup.devmobile.labo.cocktails.models;


public class Ingredient {
	private int _id;
	private String nom;
	private int image;
	
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
