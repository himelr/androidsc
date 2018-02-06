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

public class PresidentUpdateViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    public PresidentUpdateViewModel(@NonNull Application application) {

        super(application);
        appDatabase = AppDatabase.getAppDatabase(this.getApplication());
    }
    public void upddatePresident(final President president) {
        new PresidentUpdateViewModel.updateAsyncTask(appDatabase).execute(president);
    }
    private static class updateAsyncTask extends AsyncTask<President, Void, Void> {

        private AppDatabase db;

       updateAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final President... params) {
            db.presidentDao().update(params[0]);
            return null;
        }

    }
}
