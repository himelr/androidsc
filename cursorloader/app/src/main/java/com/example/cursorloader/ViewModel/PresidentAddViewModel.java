package com.example.cursorloader.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.example.cursorloader.AppDatabase;
import com.example.cursorloader.President;

/**
 * Created by HimelR on 06-Feb-18.
 */

public class PresidentAddViewModel extends AndroidViewModel{

    private AppDatabase appDatabase;
    public PresidentAddViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getAppDatabase(this.getApplication());

    }
    public void addPresident(final President president) {
        new addAsyncTask(appDatabase).execute(president);
    }
    private static class addAsyncTask extends AsyncTask<President, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final President... params) {
            db.presidentDao().insertAll(params[0]);
            return null;
        }

    }
}
