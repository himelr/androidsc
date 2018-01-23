package com.example.adaptertest;

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

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HimelR on 23-Jan-18.
 */

class CustomAdapter implements ListAdapter {
    private ArrayList<President> presidents;
    private Context context;
    private Set<DataSetObserver> observerChangeSet;
    private int item_layout;

    public CustomAdapter(ArrayList<President> presidentArrayList, Context context, int item_layout) {
        this.presidents = presidentArrayList;
        this.context=context;
        this.observerChangeSet = new HashSet<>();
        this.item_layout = item_layout;
        this.observerChangeSet.add(new ObserverChange("Observer1"));
        this.observerChangeSet.add(new ObserverChange("Observer2"));



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
        return (long) position;
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
        TextView tvName =  convertView.findViewById(R.id.textName);
        ImageView iv = convertView.findViewById(R.id.iv);
        final String imgURL  = "https://www.google.com/images/srpr/logo11w.png";

        President p = presidents.get(position);
        tvName.setText(p.getName());
        new DownLoadImageTask(iv).execute(imgURL);





        return convertView;    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}
