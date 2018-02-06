package com.example.cursorloader.Room;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cursorloader.AppDatabase;
import com.example.cursorloader.President;
import com.example.cursorloader.PresidentsGlobal;
import com.example.cursorloader.R;
import com.example.cursorloader.Room.PresidentDetailsRoom;
import com.example.cursorloader.ViewModel.CustomAdapter;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        addPresidents();
        CreateAdapter createAdapter = new CreateAdapter(this);
        Thread thread = new Thread(createAdapter);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ListView lv = findViewById(R.id.presidentList);

        lv.setAdapter(new CustomAdapter(
                createAdapter.getPresidentList(),
                this.getApplicationContext(),
                R.layout.president_item)
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("test2", "onItemClick(" + l + ")");
                Intent nextActivity = new Intent(context, PresidentDetailsRoom.class);
                nextActivity.putExtra("id", l + "");
                startActivity(nextActivity);

            }
        });



    }
    private class CreateAdapter implements Runnable{
        private Context context;
        private List<President> presidentList;
        private Boolean aBoolean = false;

        CreateAdapter(Context context){
            this.context = context;

        }

        @Override
        public void run() {
            AppDatabase db = AppDatabase.getAppDatabase(context);
            if(aBoolean) {
                for (President president : PresidentsGlobal.getInstance().getPresidents()) {
                    try {
                        db.presidentDao().insertAll(president);
                    } catch (SQLiteConstraintException e) {
                        System.out.println("Error " + president);
                    }

                }
            }
            setPresidentList(db.presidentDao().getAll().getValue());
        }

        public List<President> getPresidentList() {
            return presidentList;
        }

        public void setPresidentList(List<President> presidentList) {
            this.presidentList = presidentList;
        }
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

}
