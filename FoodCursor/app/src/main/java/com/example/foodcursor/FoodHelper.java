package com.example.foodcursor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HimelR on 31-Jan-18.
 */

public class FoodHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "foodDB";
    static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "foods";
    static final String P_NAME = "name";
    static final String COL_LANG_ID = "_id";
    static final String COL_LANG_NAME = "food_name";




   FoodHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        getWritableDatabase();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_LANG_ID
                + " INTEGER PRIMARY KEY NOT NULL, " + " " + COL_LANG_NAME
                + " VARCHAR(50) NOT NULL);");
        insertFood(db,"Chicken");
        insertFood(db,"Carrot");
        insertFood(db,"Potato");
        insertFood(db,"PAsta");
        insertFood(db,"Monkey");
        insertFood(db,"Beef");
        insertFood(db,"Prawn");




    }
    private static void insertFood(SQLiteDatabase db, String name) {
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_LANG_NAME
                + ") VALUES ('" + name + "');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

}
