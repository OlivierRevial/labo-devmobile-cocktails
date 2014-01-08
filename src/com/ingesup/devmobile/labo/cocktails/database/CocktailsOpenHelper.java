package com.ingesup.devmobile.labo.cocktails.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CocktailsOpenHelper extends SQLiteOpenHelper{
	
    private static final String DATABASE_NAME 		= "cocktails.db";
    private static final int 	DATABASE_VERSION 	= 1;	 // change whenever anything in the database changes
    public 	static final int 	UNUSED_FLAG = -1;
    
    public CocktailsOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
	public synchronized void close() {
	    super.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CocktailTable.TABLE_CREATE);
		db.execSQL(IngredientTable.TABLE_CREATE);
		db.execSQL(CocktailsIngredientsTable.TABLE_CREATE);
	}
 
	public static final class CocktailTable {
        public static final String TABLE_NAME 			= "cocktails";
        public static final String DEFAULT_SORT_ORDER 	= "_id ASC";

        public static final String 	_ID 					= "_id";
        public static final String 	COLUMN_NAME				= "name";
        public static final String 	COLUMN_RECIPE			= "recipe";
        public static final String 	COLUMN_NOTE				= "note";

        public static final String TABLE_CREATE = 
        		"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + CocktailTable._ID 							+ " INTEGER PRIMARY KEY,"
                + CocktailTable.COLUMN_NAME						+ " TEXT,"
                + CocktailTable.COLUMN_RECIPE					+ " TEXT,"
                + CocktailTable.COLUMN_NOTE						+ " INTEGER"
                + ");";
	}
	 
	public static final class IngredientTable {
        public static final String TABLE_NAME 				= "ingredients";
        public static final String DEFAULT_SORT_ORDER 		= "_id ASC";

        public static final String 	_ID 					= "_id";
        public static final String 	COLUMN_NAME				= "name";
        public static final String 	COLUMN_IS_IN_BAR 		= "is_in_bar";
        
        public static final String TABLE_CREATE = 
        		"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + IngredientTable._ID 							+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + IngredientTable.COLUMN_NAME					+ " INTEGER,"
                + IngredientTable.COLUMN_IS_IN_BAR				+ " INTEGER DEFAULT 0"
                + ");";
	}
	
	public static final class CocktailsIngredientsTable {
        public static final String TABLE_NAME 			= "cocktails_ingredients";
        public static final String DEFAULT_SORT_ORDER 	= "_id ASC";

        public static final String 	_ID 					= "_id";
        public static final String 	COLUMN_COCKTAIL_ID		= "cocktail_id";
        public static final String 	COLUMN_INGREDIENT_ID	= "ingredient_id";
        
        public static final String TABLE_CREATE = 
        		"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + CocktailsIngredientsTable._ID 								+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CocktailsIngredientsTable.COLUMN_COCKTAIL_ID					+ " INTEGER,"
                + CocktailsIngredientsTable.COLUMN_INGREDIENT_ID				+ " INTEGER,"
                + "FOREIGN KEY(" + CocktailsIngredientsTable.COLUMN_COCKTAIL_ID	+ ") REFERENCES " 	+ CocktailTable.TABLE_NAME + "(" + CocktailTable._ID + "),"
                + "FOREIGN KEY(" + CocktailsIngredientsTable.COLUMN_INGREDIENT_ID + ") REFERENCES " 	+ IngredientTable.TABLE_NAME + "(" + IngredientTable._ID + ")"
        
                + ");";
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}
}
