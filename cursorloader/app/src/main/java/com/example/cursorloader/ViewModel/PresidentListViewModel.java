package com.example.cursorloader.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.cursorloader.AppDatabase;
import com.example.cursorloader.President;

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
          AppDatabase db =  AppDatabase.getAppDatabase(context);
          List<President> pre = db.presidentDao().getAll();
          prslist = pre;
          presidents.postValue(pre);

        }
    }


}
