package com.example.adaptertest;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Context context = this;
    PresidentsHelper dbHelper = null;
    SQLiteDatabase db = null;
    SimpleCursorAdapter myAdapter;
    static final String[] PROJECTION = new String[] { "_id", "name"};
    static final String SELECTION = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] fromColumns = {"name"};
        int[] toViews = {android.R.id.text1};
        myAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                null, fromColumns, toViews, 0);

        ListView lv = findViewById(R.id.presidentList);
        lv.setAdapter(myAdapter);
        getLoaderManager().initLoader(0, null, this);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("test2", "onItemClick(" + i + ")");
//                Intent nextActivity = new Intent(context, PresidentDetails.class);
//                nextActivity.putExtra("presidentName", PresidentsGlobal.getInstance().getPresidents().get(i).getName() + "");
//                nextActivity.putExtra("startYear", PresidentsGlobal.getInstance().getPresidents().get(i).getStartYear() + "");
//                nextActivity.putExtra("endYear", PresidentsGlobal.getInstance().getPresidents().get(i).getEndYear() +"");
//                nextActivity.putExtra("img", PresidentsGlobal.getInstance().getPresidents().get(i).getImg() +"");
//                startActivity(nextActivity);
//            }
//        });


    }

    static void addPresidents(){

        if(PresidentsGlobal.getInstance().getPresidents().isEmpty()){
            PresidentsGlobal.getInstance().addPresident(new President("Ståhlberg, Kaarlo Juho",1919,1925,"http://www.presidentti.fi/public/download.aspx?ID=44362&GUID={0FC2CA27-5FB2-4F3E-BB29-D9B9CE8EFCA5}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Relander, Lauri Kristian",1925,1931,"http://www.presidentti.fi/public/download.aspx?ID=44363&GUID={4C489246-AE16-4068-A52F-C1F9A1B9D2D5}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Svinhufvud, Pehr Evind",1931,1937,"http://www.presidentti.fi/public/download.aspx?ID=44364&GUID={6FE9116A-CAE0-4DE2-B5FC-6D2B326F4B96}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Kallio, Kyösti",1937,1940,"http://www.presidentti.fi/public/download.aspx?ID=44365&GUID={634A2595-5A0D-4EA0-A38F-DD2E86F65C07}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Ryti, Risto Heikki",1940,1944,"http://www.presidentti.fi/public/download.aspx?ID=44366&GUID={0573E1B8-C894-45D2-9198-96EF1A760D68}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Mannerheim, Carl Gustaf Emil",1944,1946,"http://www.presidentti.fi/public/download.aspx?ID=44367&GUID={E1184C16-23D4-4DD9-B103-C1C80EFFDE11}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Paasikivi, Juho Kusti",1946,1956,"http://www.presidentti.fi/public/download.aspx?ID=44368&GUID={5F3374D7-CDCD-47B7-8D7C-846D103260D4}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Kekkonen, Urho Kaleva",1956,1982,"http://www.presidentti.fi/public/download.aspx?ID=44369&GUID={1F9BAFE2-0F66-4742-8ABD-E1C37513926B}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Koivisto, Mauno Henrik",1982,1994,"http://www.presidentti.fi/public/download.aspx?ID=44370&GUID={3A6C9131-7E07-438A-8859-981D9B3AE5F6}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Ahtisaari, Martti Oiva Kalevi",1994,2000,"http://www.presidentti.fi/public/download.aspx?ID=44371&GUID={C883942D-35AE-4604-BD79-9116CCBD9A16}&maxwidth=565&maxheight=500"));
            PresidentsGlobal.getInstance().addPresident(new President("Halonen, Tarja Kaarina",2000,2012,"http://www.presidentti.fi/public/download.aspx?id=91187&guid={0DB229FB-8420-4E97-9682-6F33F5750C39}&maxwidth=565&maxheight=500"));
        }






    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Uri.parse("content://com.example.presidents/presidents"),
                PROJECTION, SELECTION, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        myAdapter.swapCursor(data);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myAdapter.swapCursor(null);
    }
}
