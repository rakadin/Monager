package com.example.monager.activities.models;

public class GroupNumber {
    private int GNum_id;
    private String Gname;
    private int Gnum;
    private int DateCreated;

    public GroupNumber(int GNum_id, String gname, int gnum, int dateCreated) {
        this.GNum_id = GNum_id;
        Gname = gname;
        Gnum = gnum;
        DateCreated = dateCreated;
    }

    public int getGNum_id() {
        return GNum_id;
    }

    public void setGNum_id(int GNum_id) {
        this.GNum_id = GNum_id;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public int getGnum() {
        return Gnum;
    }

    public void setGnum(int gnum) {
        Gnum = gnum;
    }

    public int getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(int dateCreated) {
        DateCreated = dateCreated;
    }
}
