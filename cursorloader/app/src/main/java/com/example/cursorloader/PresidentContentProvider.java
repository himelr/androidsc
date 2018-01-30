package com.example.cursorloader;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by HimelR on 30-Jan-18.
 */

public class PresidentContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.android.presidents";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + PresidentsHelper.TABLE_NAME);
    private PresidentsHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new PresidentsHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(PresidentsHelper.TABLE_NAME);
        String orderBy = PresidentsHelper.COL_LANG_NAME + " asc";
        Cursor cursor = qb.query(dbHelper.getReadableDatabase(),
                new String[] { PresidentsHelper.COL_LANG_ID,
                        PresidentsHelper.COL_LANG_NAME }, null,
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

}
