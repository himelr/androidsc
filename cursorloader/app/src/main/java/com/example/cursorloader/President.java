package com.example.cursorloader;

/**
 * Created by HimelR on 23-Jan-18.
 */

class President {
    private String name;
    private int startYear;
    private int endYear;
    private String img;

    President(String name, int startYear, int endYear, String img){
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.img = img;

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
        return name + " " + startYear + "-" + endYear;
    }

    public String getImg() {
        return img;
    }
}
