package com.example.cursorloader;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Created by HimelR on 30-Jan-18.
 */

public class PresidentContentProvider extends ContentProvider {
    SQLiteDatabase thisDB;
    private static final String AUTHORITY = "com.android.presidents";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + PresidentsHelper.TABLE_NAME);
    private PresidentsHelper dbHelper;
    private static final UriMatcher uriMatcher;
    private static final int PRESIDENT_ID = 3;
    public static final String _ID = "_id";



    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PresidentsHelper.TABLE_NAME + "/#", PRESIDENT_ID);
    }



    @Override
    public boolean onCreate() {
        dbHelper = new PresidentsHelper(getContext());
        thisDB = dbHelper.getReadableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(PresidentsHelper.TABLE_NAME);
        String orderBy = PresidentsHelper.COL_LANG_NAME + " asc";
        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case PRESIDENT_ID:
                qb.appendWhere(PresidentsHelper.COL_LANG_ID + "="
                        + uri.getLastPathSegment());
                Log.d("test2", "Query");
                break;
            default:
                break;

        }

//        if (uriMatcher.match(uri) == PRESIDENT_ID)
//           qb.appendWhere(_ID + " = " + uri.getPathSegments().get(1));

        Cursor cursor = qb.query(dbHelper.getReadableDatabase(),
                new String[] { PresidentsHelper.COL_LANG_ID,
                        PresidentsHelper.COL_LANG_NAME, PresidentsHelper.COL_LANG_YEARS }, null,
                null, null, null, orderBy);

        return cursor;
    }
    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd.com.android.presidents";
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



    public Cursor query2(long id) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(PresidentsHelper.TABLE_NAME);
        qb.appendWhere(PresidentsHelper.COL_LANG_ID + " = " + id);

        Cursor cursor = qb.query(thisDB,
                new String[] { PresidentsHelper.COL_LANG_ID,
                        PresidentsHelper.COL_LANG_NAME, PresidentsHelper.COL_LANG_YEARS }, null,
                null, null, null, null);

        return cursor;
}


}
