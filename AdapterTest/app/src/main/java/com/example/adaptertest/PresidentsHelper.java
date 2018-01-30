package com.example.adaptertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HimelR on 30-Jan-18.
 */

class PresidentsHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "presidentsDB";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "presidents";
    static final String P_NAME = "name";



    PresidentsHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_NAME);
        ContentValues values = new ContentValues();
        values.put(P_NAME,"Makke");
        db.insert(TABLE_NAME,null,values);
        System.out.println(values.toString());
        System.out.println("DDddd");
        values.clear();




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
