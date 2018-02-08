package com.example.cursorloader.Cursor;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cursorloader.R;

public class PresidentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_details);
        Intent intent = getIntent();
        String message = intent.getStringExtra("id");

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);


        long _id = Long.valueOf(message).longValue();
        Uri uri = Uri.parse(PresidentContentProvider.CONTENT_URI + "/"
                + _id);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String name = "";
        String year = "";

        if (cursor != null) {
            cursor.moveToFirst();
            name = cursor.getString(cursor
                    .getColumnIndexOrThrow(PresidentsHelper.COL_LANG_NAME));
            year = cursor.getString(cursor
                    .getColumnIndexOrThrow(PresidentsHelper.COL_LANG_YEARS));

            /*for (int i = 0; i < mCategory.getCount(); i++) {

                String s = (String) mCategory.getItemAtPosition(i);
                if (s.equalsIgnoreCase(category)) {
                    mCategory.setSelection(i);
                }
            }*/


        }

        textView.setText(name + "");
        textView2.setText(year+ "");


    }
}
