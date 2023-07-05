package com.example.monager.activities.models;

public class Year {
    private int Yid;
    private String Yname;
    private int YmoneyInTotal;
    private int YmoneyOutTotal;


    public int getYid() {
        return Yid;
    }

    public void setYid(int yid) {
        Yid = yid;
    }

    public String getYname() {
        return Yname;
    }

    public void setYname(String yname) {
        Yname = yname;
    }

    public int getYmoneyInTotal() {
        return YmoneyInTotal;
    }

    public void setYmoneyInTotal(int ymoneyInTotal) {
        YmoneyInTotal = ymoneyInTotal;
    }

    public int getYmoneyOutTotal() {
        return YmoneyOutTotal;
    }

    public void setYmoneyOutTotal(int ymoneyOutTotal) {
        YmoneyOutTotal = ymoneyOutTotal;
    }
}
