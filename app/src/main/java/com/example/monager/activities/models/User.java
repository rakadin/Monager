package com.example.monager.activities.models;

public class User {
    private String UserName;
    private String UserSurname;
    private String UserPass;
    private int Salary;
    private int TotalSave;
    private int MSpendingLevel;
    private int BonusMoney;
    private int YourLoan;
    private int SalTime;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserSurname() {
        return UserSurname;
    }

    public void setUserSurname(String userSurname) {
        UserSurname = userSurname;
    }

    public String getUserPass() {
        return UserPass;
    }

    public void setUserPass(String userPass) {
        UserPass = userPass;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getTotalSave() {
        return TotalSave;
    }

    public void setTotalSave(int totalSave) {
        TotalSave = totalSave;
    }

    public int getMSpendingLevel() {
        return MSpendingLevel;
    }

    public void setMSpendingLevel(int MSpendingLevel) {
        this.MSpendingLevel = MSpendingLevel;
    }

    public int getBonusMoney() {
        return BonusMoney;
    }

    public void setBonusMoney(int bonusMoney) {
        BonusMoney = bonusMoney;
    }

    public int getYourLoan() {
        return YourLoan;
    }

    public void setYourLoan(int yourLoan) {
        YourLoan = yourLoan;
    }

    public int getSalTime() {
        return SalTime;
    }

    public void setSalTime(int salTime) {
        SalTime = salTime;
    }
}
