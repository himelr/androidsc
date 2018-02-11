package com.example.cursorloader.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cursorloader.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrsFrgDetails extends Fragment {


    public PrsFrgDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    public void setText(String text) {
        TextView view = (TextView) getView().findViewById(R.id.textPrs);
        view.setText(text);
    }
}
