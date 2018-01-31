package com.example.cursorloader;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity implements
        LoaderCallbacks<Cursor> {
    private Context context = this;
    private static final int LOADER_ID = 42;
    private CursorAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_list);
        int[] toViews = {R.id.list_item};


        _adapter = new SimpleCursorAdapter(this, R.layout.container_list_item_view,
                null, new String[] { PresidentsHelper.COL_LANG_NAME }, toViews, 0);

        setListAdapter(_adapter);

        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("test2", "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(context, PresidentDetails.class);
                nextActivity.putExtra("id", l + "");
                startActivity(nextActivity);

            }
        });

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id != LOADER_ID) {
            return null;
        }
        return new CursorLoader(MainActivity.this,
                PresidentContentProvider.CONTENT_URI,
                new String[] { PresidentsHelper.COL_LANG_ID, PresidentsHelper.COL_LANG_NAME, PresidentsHelper.COL_LANG_YEARS }, null, null,
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
