package com.example.adaptertest;


import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by HimelR on 23-Jan-18.
 */

public class PresidentsGlobal {

    private static PresidentsGlobal instance;
    private ArrayList<President> presidents = new ArrayList();
    private PresidentsGlobal(){}

    static{
        try{
            instance = new PresidentsGlobal();
        }catch(Exception e){
            throw new RuntimeException("Error occurred");
        }
    }

    public static PresidentsGlobal getInstance(){
        return instance;
    }
    public ArrayList<President> getPresidents() {
        return presidents;
    }
    public void addPresident(President p){
        presidents.add(p);
    }
}
