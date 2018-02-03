package com.example.cursorloader;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

/**
 * Created by HimelR on 02-Feb-18.
 */

public class PresidentListViewModel extends ViewModel {
    private MutableLiveData<List<President>> presidents;
    private MutableLiveData<President> president;
    private List<President> prslist;
    private Context context;


    public LiveData<List<President>> getPresidents() {

            presidents = new MutableLiveData<List<President>>();
            loadUsers();

        return presidents;
    }
    public LiveData<President> getPresident(int pos){
        president = new MutableLiveData<>();
        president.setValue(prslist.get(pos));
        return president;

    }

    private void loadUsers() {
        Thread task = new Thread(new TaskB(getContext()));
        task.start();

    }
    private void loadUser() {
        Thread task = new Thread(new TaskC(getContext()));
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
          AppDatabase db =  AppDatabase.getAppDatabase(context);
          List<President> pre = db.presidentDao().getAll();
          prslist = pre;
          presidents.postValue(pre);

        }
    }
    private class TaskC implements Runnable{
        private Context context;

        private TaskC(Context context){
            this.context = context;
        }

        @Override
        public void run() {
            AppDatabase db =  AppDatabase.getAppDatabase(context);


        }
    }

}
