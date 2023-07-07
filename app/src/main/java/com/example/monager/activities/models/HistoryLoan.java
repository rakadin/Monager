package com.example.monager.activities.models;

public class HistoryLoan {
    private int HLoanID;
    private int value;
    private String loanDate;
    private String loanNote;

    public int getHLoanID() {
        return HLoanID;
    }

    public void setHLoanID(int HLoanID) {
        this.HLoanID = HLoanID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanNote() {
        return loanNote;
    }

    public void setLoanNote(String loanNote) {
        this.loanNote = loanNote;
    }
}
