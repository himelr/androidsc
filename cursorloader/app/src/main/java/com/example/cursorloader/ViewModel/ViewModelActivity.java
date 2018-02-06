package com.example.cursorloader.ViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cursorloader.AppDatabase;
import com.example.cursorloader.Room.PresidentDetailsRoom;
import com.example.cursorloader.R;

public class ViewModelActivity extends AppCompatActivity implements View.OnCreateContextMenuListener{

    private Context context;
    private PresidentListViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        ListView lv = findViewById(R.id.modelList);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefresh);
        registerForContextMenu(lv);
        context = getApplicationContext();

        model = ViewModelProviders.of(this).get(PresidentListViewModel.class);
        model.setContext(context);
        model.getPresidents().observe(this, presidents -> {
            Log.d("test2", "Data set changed");

            lv.setAdapter(new CustomAdapter(
                    presidents,
                    context,
                    R.layout.president_item)
            );

        });

        lv.setOnItemClickListener((adapterView, view, i, l) -> {

            Log.d("test2", "onItemClick(" + i + ")");
            Intent nextActivity = new Intent(this.getApplicationContext(), PresidentDetailsRoom.class);
            nextActivity.putExtra("id", l + "");
            startActivity(nextActivity);
        });

        swipeRefreshLayout.setOnRefreshListener(
                () -> {
                    Log.i("test2", "onRefresh called from SwipeRefreshLayout");

                    super.recreate();

                }
        );

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.d("test2", item.toString()+ "item");
        Log.d("test2", item.getItemId()+ "item2");
        Log.d("test2", info.position+ "item3");
        Log.d("test2", info.id+ "item4");

        switch (item.getItemId()) {
            case R.id.add:
                System.out.println("ADD");
                Context context = getApplicationContext();
                CharSequence text = "Adding president!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent intent =  new Intent(context,PresidentAdd.class);
                startActivity(intent);
                return true;

            case R.id.update:
                Intent intent2 = new Intent(this.getApplicationContext(),PresidentUpdate.class);
                intent2.putExtra("id", (info.id + 1) + "");
                startActivity(intent2);

                return true;

            case R.id.delete:
                System.out.println("DELETE");

                AlertDialog alertDialog = new AlertDialog.Builder(ViewModelActivity.this).create();
                alertDialog.setTitle("President");
                alertDialog.setMessage("Delete");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Thread thread = new Thread(new TaskB3((int)info.id + 1));
                                thread.start();
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                //dialog.dismiss();
                            }
                        });
                alertDialog.show();
                //super.recreate();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }



    private class TaskB3 implements Runnable{

        private int id;

        private TaskB3(int id){
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(id + "id");
            AppDatabase db =  AppDatabase.getAppDatabase(context);
            db.presidentDao().deleteID(id);

        }
    }



}
