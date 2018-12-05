package com.restaurantreservation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "RestaurantReservation.db";
    private static final int DATABASE_VERSION = 1;

    private String[] tables;
    private String[] tableCreatorString;

    // Constructor
    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Initialize database
    public void InitDatabase(String[] tables, String tableCreatorString[]){
        this.tables = tables;
        this.tableCreatorString = tableCreatorString;
    }

    // Create the database
    public void CreateDatabase(Context context){
        SQLiteDatabase database;
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    // Delete the database
    public void deleteDatabase(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Drop existing tables
        for(int i = 0; i < tables.length; i++){
            db.execSQL("DROP TABLE IF EXISTS " + tables[i]);
        }

        // Create tables
        for(int i = 0; i < tables.length; i++){
            db.execSQL(tableCreatorString[i]);
        }
    }



}
