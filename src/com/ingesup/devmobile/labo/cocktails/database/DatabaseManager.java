package com.ingesup.devmobile.labo.cocktails.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ingesup.devmobile.labo.cocktails.database.CocktailsOpenHelper.CocktailTable;
import com.ingesup.devmobile.labo.cocktails.database.CocktailsOpenHelper.CocktailsIngredientsTable;
import com.ingesup.devmobile.labo.cocktails.database.CocktailsOpenHelper.IngredientTable;
import com.ingesup.devmobile.labo.cocktails.models.Cocktail;
import com.ingesup.devmobile.labo.cocktails.models.CocktailImages;
import com.ingesup.devmobile.labo.cocktails.models.Ingredient;
import com.ingesup.devmobile.labo.cocktails.models.IngredientImages;

public class DatabaseManager {
	private static DatabaseManager _instance;
	private CocktailsOpenHelper cocktailsOpenHelper;
	
	public static DatabaseManager getInstance() {
		if(_instance == null) {
			_instance = new DatabaseManager();
		}
		return _instance;
	}
	
	public void init(Context context) {
		if(cocktailsOpenHelper == null) {
			cocktailsOpenHelper = new CocktailsOpenHelper(context);
		}
	}
	
	public CocktailsOpenHelper getDbHelper() {
		return cocktailsOpenHelper;
	}
	
	public boolean hasAlreadyCocktails() {
		SQLiteDatabase db = cocktailsOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(CocktailTable.TABLE_NAME, null, null, null, null, null, null);
		int count = cursor.getCount();
		db.close();
		
		return count != 0;
	}
	
	public List<Cocktail> getAllCocktails() {
		CocktailImages cocktailsImages = new CocktailImages();
		SQLiteDatabase db = cocktailsOpenHelper.getReadableDatabase();
		List<Cocktail> cocktails = new ArrayList<Cocktail>();

		Cursor cursor = db.query(CocktailTable.TABLE_NAME, null, null, null, null, null, null);

		while(cursor.moveToNext()) {
			Cocktail cocktail = new Cocktail();
			cocktail.setId(cursor.getInt(cursor.getColumnIndex(CocktailTable._ID)));
			cocktail.setNom(cursor.getString(cursor.getColumnIndex(CocktailTable.COLUMN_NAME)));
			cocktail.setNote(cursor.getInt(cursor.getColumnIndex(CocktailTable.COLUMN_NOTE)));
			cocktail.setRecette(cursor.getString(cursor.getColumnIndex(CocktailTable.COLUMN_RECIPE)));
			cocktail.setImage(cocktailsImages.getCocktailDrawable(cocktail.getNom()));
			cocktails.add(cocktail);
		}
		
		db.close();
		
		for(Cocktail cocktail : cocktails) {
			cocktail.setIngredients(getCocktailIngredients(cocktail));
		}
		
		return cocktails;
	}
	
	public List<Ingredient> getAllIngredients() {
		SQLiteDatabase db = cocktailsOpenHelper.getReadableDatabase();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Cursor cursor = db.query(IngredientTable.TABLE_NAME, null, null, null, null, null, null);
		while(cursor.moveToNext()) {
			Ingredient ingredient = new Ingredient();
			ingredient.setId(cursor.getInt(cursor.getColumnIndex(IngredientTable._ID)));
			ingredient.setNom(cursor.getString(cursor.getColumnIndex(IngredientTable.COLUMN_NAME)));
			ingredients.add(ingredient);
		}
		
		db.close();
		
		return ingredients;
	}
	
	public List<Ingredient> getCocktailIngredients(Cocktail cocktail) {
		IngredientImages ingredientImages = new IngredientImages();
		SQLiteDatabase db = cocktailsOpenHelper.getReadableDatabase();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		String cocktailIngredientsQuery = getQueryWithArgs("SELECT a.* FROM ? a INNER JOIN ? b ON a.?=b.? WHERE b.? = ?", new String[] {
				IngredientTable.TABLE_NAME, CocktailsIngredientsTable.TABLE_NAME,
				IngredientTable._ID, CocktailsIngredientsTable.COLUMN_INGREDIENT_ID,
				CocktailsIngredientsTable.COLUMN_COCKTAIL_ID, String.valueOf(cocktail.getId())
				});
		Cursor cocktailIngredientsCursor = db.rawQuery(cocktailIngredientsQuery, null);

		while(cocktailIngredientsCursor.moveToNext()) {
			Ingredient ingredient = new Ingredient();
			ingredient.setId(cocktailIngredientsCursor.getInt(cocktailIngredientsCursor.getColumnIndex(IngredientTable._ID)));
			ingredient.setNom(cocktailIngredientsCursor.getString(cocktailIngredientsCursor.getColumnIndex(IngredientTable.COLUMN_NAME)));
			ingredient.setImage(ingredientImages.getIngredientDrawable(ingredient.getNom()));
			ingredients.add(ingredient);
		}
		
		db.close();
		
		return ingredients;
	}
	
	public void insertCocktail(Cocktail cocktail) {
		ContentValues cv = new ContentValues();
		cv.put(CocktailTable.COLUMN_NAME, cocktail.getNom());
		cv.put(CocktailTable.COLUMN_NOTE, cocktail.getNote());
		cv.put(CocktailTable.COLUMN_RECIPE, cocktail.getRecette());
		long cocktailId = insertRow(CocktailTable.TABLE_NAME, cv);
		
		for(Ingredient ingredient : cocktail.getIngredients()) {
			long ingredientId = doesIngredientExists(ingredient);
			if(ingredientId == -1) {
				ingredientId = insertIngredient(ingredient);
			}
			insertCocktailIngredient(cocktailId, ingredientId);
		}
	}
	
	public long insertIngredient(Ingredient ingredient) {
		ContentValues cv = new ContentValues();
		cv.put(IngredientTable.COLUMN_NAME, ingredient.getNom());
		return insertRow(IngredientTable.TABLE_NAME, cv);
	}
	
	public void insertCocktailIngredient(long cocktailId, long ingredientId) {
		ContentValues cv = new ContentValues();
		cv.put(CocktailsIngredientsTable.COLUMN_COCKTAIL_ID, cocktailId);
		cv.put(CocktailsIngredientsTable.COLUMN_INGREDIENT_ID, ingredientId);
		insertRow(CocktailsIngredientsTable.TABLE_NAME, cv);
	}
	
	private long doesIngredientExists(Ingredient ingredient) {
		SQLiteDatabase db = cocktailsOpenHelper.getReadableDatabase();
		String ingredientsQuery = getQueryWithArgs("SELECT * FROM ? WHERE ? = '?'", new String[] {
				IngredientTable.TABLE_NAME, 
				IngredientTable.COLUMN_NAME, ingredient.getNom()
				});
		Cursor ingredientsCursor = db.rawQuery(ingredientsQuery, null);
		long rowId = -1;
		if(ingredientsCursor.moveToFirst()) {
			rowId = ingredientsCursor.getInt(ingredientsCursor.getColumnIndex(IngredientTable._ID));
		}
		
		db.close();

		return rowId;
	}
	
	private long insertRow(String table, ContentValues cv) {
		SQLiteDatabase db = cocktailsOpenHelper.getWritableDatabase();
		long rowId = db.insert(table, null, cv);
		db.close();
		
		return rowId;
	}
	
	private int updateRow(String table, ContentValues cv, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = cocktailsOpenHelper.getWritableDatabase();
		int nbRowsUpdated = db.update(table, cv, whereClause, whereArgs);
		db.close();
		
		return nbRowsUpdated;
	}
	
	private int deleteRows(String table, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = cocktailsOpenHelper.getWritableDatabase();
		int nbDeleted = db.delete(table, whereClause, whereArgs);
		db.close();
		
		return nbDeleted;
	}
	
	private String getQueryWithArgs(String baseQuery, String[] args) {
		String replacedQuery = baseQuery;
		int nbReplaced = 0;
		while(replacedQuery.contains("?") && nbReplaced < args.length) {
			replacedQuery = replacedQuery.replaceFirst("\\?", args[nbReplaced]);
			nbReplaced++;
		}
		return replacedQuery;
	}
}
