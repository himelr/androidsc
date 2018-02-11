package com.example.cursorloader.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.cursorloader.President;
import com.example.cursorloader.R;

public class FragView extends Activity implements ItemFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_view);
    }

    @Override
    public void onListFragmentInteraction(President item) {
        PrsFrgDetails fragment = (PrsFrgDetails) getFragmentManager().findFragmentById(R.id.detailFragment);
        fragment.setText(item.getName() + "\n" + item.getStartYear() + "-" + item.getEndYear());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack("FragmentB");
        transaction.commit();
    }
}
