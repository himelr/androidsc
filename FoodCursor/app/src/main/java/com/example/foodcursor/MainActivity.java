package com.example.foodcursor;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity implements
        LoaderManager.LoaderCallbacks<Cursor>  {

    private static final int LOADER_ID = 43;
    private CursorAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_list);
        int[] toViews = {R.id.list_item};


        _adapter = new SimpleCursorAdapter(this, R.layout.container_list_item_view,
                null, new String[] { FoodHelper.COL_LANG_NAME }, toViews, 0);

        setListAdapter(_adapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id != LOADER_ID) {
            return null;
        }
        return new CursorLoader(MainActivity.this,
                FoodContentProvider.CONTENT_URI,
                new String[] { FoodHelper.COL_LANG_ID, FoodHelper.COL_LANG_NAME}, null, null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        _adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        _adapter.swapCursor(null);
    }
}
