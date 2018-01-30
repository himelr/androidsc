package com.example.cursorloader;

/**
 * Created by HimelR on 30-Jan-18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HimelR on 30-Jan-18.
 */

class PresidentsHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "presidentsDB";
    static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "presidents";
    static final String P_NAME = "name";
    static final String COL_LANG_ID = "_id";
    static final String COL_LANG_NAME = "lang_name";
    static final String COL_LANG_YEARS = "lang_years";



    PresidentsHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        getWritableDatabase();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_LANG_ID
                + " INTEGER PRIMARY KEY NOT NULL, " + " " + COL_LANG_NAME
                + " VARCHAR(50) NOT NULL,"+" " +COL_LANG_YEARS + " VARCHAR(50) NOT NULL);");
        addPresidents(db);


    }
    private static void insertPresident(SQLiteDatabase db, String name, String years) {
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_LANG_NAME
                + ","+ COL_LANG_YEARS +") VALUES ('" + name + "','"+ years +"');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public static void addPresidents(SQLiteDatabase db){
        for(President president : PresidentsGlobal.getInstance().getPresidents()){
            insertPresident(db,president.getName(),president.getStartYear() + "-" + president.getEndYear());
        }


    }
}
