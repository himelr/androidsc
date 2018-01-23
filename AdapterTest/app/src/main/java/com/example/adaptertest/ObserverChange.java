package com.example.adaptertest;

import android.database.DataSetObserver;
import android.util.Log;

/**
 * Created by HimelR on 23-Jan-18.
 */

public class ObserverChange extends DataSetObserver {
    private String name;

    public ObserverChange(String name){
        this.name = name;
    }
    @Override
    public void onChanged() {
        super.onChanged();
        Log.d("test2","data changed: " + this.name);
    }
}
