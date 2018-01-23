package com.example.adaptertest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = findViewById(R.id.presidentList);
        addPresidents();



        lv.setAdapter(new CustomAdapter(PresidentsGlobal.getInstance().getPresidents(),this,
                R.layout.item_row
               )
        );
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("test2", "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(context, PresidentDetails.class);
                nextActivity.putExtra("presidentName", PresidentsGlobal.getInstance().getPresidents().get(i).getName() + "");
                nextActivity.putExtra("startYear", PresidentsGlobal.getInstance().getPresidents().get(i).getStartYear() + "");
                nextActivity.putExtra("endYear", PresidentsGlobal.getInstance().getPresidents().get(i).getEndYear() +"");
                startActivity(nextActivity);
            }
        });


    }

    static void addPresidents(){
        PresidentsGlobal.getInstance().addPresident(new President("Ståhlberg, Kaarlo Juho",1919,1925,"http://www.presidentti.fi/public/download.aspx?ID=44362&GUID={0FC2CA27-5FB2-4F3E-BB29-D9B9CE8EFCA5}&maxwidth=565&maxheight=500"));
        PresidentsGlobal.getInstance().addPresident(new President("Relander, Lauri Kristian",1925,1931,"http://www.presidentti.fi/public/download.aspx?ID=44363&GUID={4C489246-AE16-4068-A52F-C1F9A1B9D2D5}&maxwidth=565&maxheight=500"));
        PresidentsGlobal.getInstance().addPresident(new President("Svinhufvud, Pehr Evind",1931,1937,"http://www.presidentti.fi/public/download.aspx?ID=44364&GUID={6FE9116A-CAE0-4DE2-B5FC-6D2B326F4B96}&maxwidth=565&maxheight=500"));
        PresidentsGlobal.getInstance().addPresident(new President("Kallio, Kyösti",1937,1940,"http://www.presidentti.fi/public/download.aspx?ID=44365&GUID={634A2595-5A0D-4EA0-A38F-DD2E86F65C07}&maxwidth=565&maxheight=500"));
        PresidentsGlobal.getInstance().addPresident(new President("Ryti, Risto Heikki",1940,1944,""));
        PresidentsGlobal.getInstance().addPresident(new President("Mannerheim, Carl Gustaf Emil",1944,1946));
        PresidentsGlobal.getInstance().addPresident(new President("Paasikivi, Juho Kusti",1946,1956));
        PresidentsGlobal.getInstance().addPresident(new President("Kekkonen, Urho Kaleva",1956,1982));
        PresidentsGlobal.getInstance().addPresident(new President("Koivisto, Mauno Henrik",1982,1994));
        PresidentsGlobal.getInstance().addPresident(new President("Ahtisaari, Martti Oiva Kalevi",1994,2000));
        PresidentsGlobal.getInstance().addPresident(new President("Halonen, Tarja Kaarina",2000,2012));






    }
}
