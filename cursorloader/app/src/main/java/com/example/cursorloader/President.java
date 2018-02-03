package com.example.cursorloader;
import android.arch.persistence.room.*;

/**
 * Created by HimelR on 23-Jan-18.
 */
@Entity
public class President {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int startYear;
    private int endYear;
    private String img;

    public President(String name, int startYear, int endYear, String img){
        this.name = name;
        this.setStartYear(startYear);
        this.setEndYear(endYear);
        this.setImg(img);

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }


    public int getEndYear() {
        return endYear;
    }

    @Override
    public String toString() {
        return name + " id:" + this.id;
    }

    public String getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
