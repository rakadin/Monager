package com.example.monager.activities.models;

public class History {
    private String HistoryName;
    private String Type;
    private int Value;
    private String HistoryDate;
    private String Note;
    private int Yid;
    private int Mid;
    private int Did;

    public History(String historyName, String type, int value, String historyDate, String note, int yid, int mid, int did) {
        HistoryName = historyName;
        Type = type;
        Value = value;
        HistoryDate = historyDate;
        Note = note;
        Yid = yid;
        Mid = mid;
        Did = did;
    }

    public String getHistoryName() {
        return HistoryName;
    }

    public void setHistoryName(String historyName) {
        HistoryName = historyName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public String getHistoryDate() {
        return HistoryDate;
    }

    public void setHistoryDate(String historyDate) {
        HistoryDate = historyDate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getYid() {
        return Yid;
    }

    public void setYid(int yid) {
        Yid = yid;
    }

    public int getMid() {
        return Mid;
    }

    public void setMid(int mid) {
        Mid = mid;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }
}
