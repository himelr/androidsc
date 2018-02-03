package com.example.cursorloader.ViewModel;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.cursorloader.President;
import com.example.cursorloader.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HimelR on 23-Jan-18.
 */

public class CustomAdapter implements ListAdapter {
    private List<President> presidents;
    private Context context;
    private Set<DataSetObserver> observerChangeSet;
    private int item_layout;

    public CustomAdapter(List<President> presidentArrayList, Context context, int item_layout) {
        this.presidents = presidentArrayList;
        this.context=context;
        this.observerChangeSet = new HashSet<>();
        this.item_layout = item_layout;


    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        observerChangeSet.add(observer);

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        observerChangeSet.remove(observer);

    }

    @Override
    public int getCount() {
        return presidents.size();
    }

    @Override
    public Object getItem(int position) {
        return presidents.indexOf(position);
    }

    @Override
    public long getItemId(int position) {
        return presidents.get(position).getId() - 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return presidents.isEmpty();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater. inflate(item_layout, null);
        }

        TextView tvName =  convertView.findViewById(R.id.item2);

        President p = presidents.get(position);
        tvName.setText(p.getName() +  "ID:" + p.getId());



        return convertView;    }



}
