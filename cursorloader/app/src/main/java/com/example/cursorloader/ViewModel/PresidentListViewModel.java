package com.example.cursorloader.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.cursorloader.AppDatabase;
import com.example.cursorloader.President;

import java.util.List;

/**
 * Created by HimelR on 02-Feb-18.
 */

public class PresidentListViewModel extends AndroidViewModel {
    private LiveData<List<President>> presidents;
    private Context context;
    private AppDatabase appDatabase;


    public PresidentListViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getAppDatabase(this.getApplication());
        presidents = appDatabase.presidentDao().getAll();
    }


    public LiveData<List<President>> getPresidents() {

        return presidents;
    }

    private void loadUsers() {
        Thread task = new Thread(new TaskB(getContext()));
        task.start();

    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private class TaskB implements Runnable{
        private Context context;

        private TaskB(Context context){
            this.context = context;
        }

        @Override
        public void run() {
          AppDatabase db =  AppDatabase.getAppDatabase(getApplication().getApplicationContext());
        /*  List<President> pre = db.presidentDao().getAll();
          presidents.postValue(pre);*/

        }
    }
    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }


        @Override
        protected Void doInBackground(Integer... integers) {
            db.presidentDao().deleteID(integers[0]);
            return null;
        }
    }


}
