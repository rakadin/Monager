package com.example.monager.activities.models;

public class GroupHistory {
    private int G_personID;
    private int GNum_id;
    private String G_type;
    private int G_money_stack;
    private int G_note;
    private String TransDate;

    public GroupHistory(int g_personID, int GNum_id, String g_type, int g_money_stack, int g_note, String transDate) {
        G_personID = g_personID;
        this.GNum_id = GNum_id;
        G_type = g_type;
        G_money_stack = g_money_stack;
        G_note = g_note;
        TransDate = transDate;
    }

    public int getG_personID() {
        return G_personID;
    }

    public void setG_personID(int g_personID) {
        G_personID = g_personID;
    }

    public int getGNum_id() {
        return GNum_id;
    }

    public void setGNum_id(int GNum_id) {
        this.GNum_id = GNum_id;
    }

    public String getG_type() {
        return G_type;
    }

    public void setG_type(String g_type) {
        G_type = g_type;
    }

    public int getG_money_stack() {
        return G_money_stack;
    }

    public void setG_money_stack(int g_money_stack) {
        G_money_stack = g_money_stack;
    }

    public int getG_note() {
        return G_note;
    }

    public void setG_note(int g_note) {
        G_note = g_note;
    }

    public String getTransDate() {
        return TransDate;
    }

    public void setTransDate(String transDate) {
        TransDate = transDate;
    }
}
