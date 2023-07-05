package com.example.monager.activities.models;

public class HistoryItem {
    private String Type;
    private int Value;
    private String HistoryDate;
    private String Note;

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
}
