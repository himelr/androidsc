package com.example.foodcursor;

/**
 * Created by HimelR on 31-Jan-18.
 */


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by HimelR on 30-Jan-18.
 */

public class FoodContentProvider extends ContentProvider {
    SQLiteDatabase thisDB;
    private static final String AUTHORITY = "com.android.foods";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + FoodHelper.TABLE_NAME);
    private FoodHelper dbHelper;
    private static final UriMatcher uriMatcher;
    private static final int FOOD_ID = 3;
    public static final String _ID = "_id";



    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, FoodHelper.TABLE_NAME + "/#", FOOD_ID);
    }



    @Override
    public boolean onCreate() {
        dbHelper = new FoodHelper(getContext());
        thisDB = dbHelper.getReadableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(FoodHelper.TABLE_NAME);
        String orderBy = FoodHelper.COL_LANG_NAME + " asc";

        Cursor cursor = qb.query(dbHelper.getReadableDatabase(),
                new String[] { FoodHelper.COL_LANG_ID,
                        FoodHelper.COL_LANG_NAME }, null,
                null, null, null, orderBy);

        return cursor;
    }
    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd.com.android.foods";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    


}
