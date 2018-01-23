package com.example.adaptertest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class PresidentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_details);
        Intent intent = getIntent();
        String message = intent.getStringExtra("presidentName");
        String message2 = intent.getStringExtra("startYear");
        String message3 = intent.getStringExtra("endYear");
        String message4 = intent.getStringExtra("img");
        Log.d("test2", message2);


        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        ImageView imageView = findViewById(R.id.iv2);
        new DownLoadImageTask(imageView).execute(message4);
        textView.setText(message);
        textView2.setText(message2+ "\n" + "-\n" + message3);
    }
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();

                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
