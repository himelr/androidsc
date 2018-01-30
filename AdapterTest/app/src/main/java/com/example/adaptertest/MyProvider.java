package com.example.adaptertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by HimelR on 30-Jan-18.
 */

class MyProvider extends ContentProvider {
    SQLiteDatabase thisDB;
    public static final String PROVIDER_NAME = "com.example.presidents";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/presidents");
    public static final String _ID = "_id";
    public static final String PRESIDENT = "name";
    private static final int PRESIDENTS = 1;
    private static final int PRESIDENT_ID = 2;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "PRESIDENTS", PRESIDENTS);
        uriMatcher.addURI(PROVIDER_NAME, "PRESIDENTS/#", PRESIDENT_ID);
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PRESIDENTS:
                return "vnd.android.cursor.dir/vnd.example.PRESIDENTS ";
            case PRESIDENT_ID:
                return "vnd.android.cursor.item/vnd.example.PRESIDENTS ";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    public boolean onCreate() {
        Context c = getContext();
        PresidentsHelper dbHelper = new PresidentsHelper(c);
        thisDB = dbHelper.getReadableDatabase();
        if (thisDB == null)
            return false;
        else
            return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection,
                          String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(PresidentsHelper.TABLE_NAME);
        if (uriMatcher.match(uri) == PRESIDENT_ID)
            sqlBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));

        Cursor cur = sqlBuilder.query(thisDB, projection, selection, selectionArgs, null, null,
                sortOrder);
        cur.setNotificationUri(getContext().getContentResolver(), uri);
        return cur;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}