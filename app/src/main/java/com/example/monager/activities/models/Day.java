package com.example.monager.activities.models;

public class Day {
    private int Did;
    private int Mid;
    private String Dname;
    private int DmoneyIn;
    private int DmoneyOut;
    private int D_moving;
    private int D_eat_drink;
    private int D_shopping;
    private int D_study;
    private int D_Hrent;
    private int D_loan;
    private int D_game;
    private int D_medical;
    private int D_bigTravel;
    private int D_otherService;
    private int Yid;

    public Day(int did, int mid, String dname, int dmoneyIn, int dmoneyOut, int d_moving, int d_eat_drink, int d_shopping, int d_study, int d_Hrent, int d_loan, int d_game, int d_medical, int d_bigTravel, int d_otherService, int yid) {
        Did = did;
        Mid = mid;
        Dname = dname;
        DmoneyIn = dmoneyIn;
        DmoneyOut = dmoneyOut;
        D_moving = d_moving;
        D_eat_drink = d_eat_drink;
        D_shopping = d_shopping;
        D_study = d_study;
        D_Hrent = d_Hrent;
        D_loan = d_loan;
        D_game = d_game;
        D_medical = d_medical;
        D_bigTravel = d_bigTravel;
        D_otherService = d_otherService;
        Yid = yid;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }

    public int getMid() {
        return Mid;
    }

    public void setMid(int mid) {
        Mid = mid;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public int getDmoneyIn() {
        return DmoneyIn;
    }

    public void setDmoneyIn(int dmoneyIn) {
        DmoneyIn = dmoneyIn;
    }

    public int getDmoneyOut() {
        return DmoneyOut;
    }

    public void setDmoneyOut(int dmoneyOut) {
        DmoneyOut = dmoneyOut;
    }

    public int getD_moving() {
        return D_moving;
    }

    public void setD_moving(int d_moving) {
        D_moving = d_moving;
    }

    public int getD_eat_drink() {
        return D_eat_drink;
    }

    public void setD_eat_drink(int d_eat_drink) {
        D_eat_drink = d_eat_drink;
    }

    public int getD_shopping() {
        return D_shopping;
    }

    public void setD_shopping(int d_shopping) {
        D_shopping = d_shopping;
    }

    public int getD_study() {
        return D_study;
    }

    public void setD_study(int d_study) {
        D_study = d_study;
    }

    public int getD_Hrent() {
        return D_Hrent;
    }

    public void setD_Hrent(int d_Hrent) {
        D_Hrent = d_Hrent;
    }

    public int getD_loan() {
        return D_loan;
    }

    public void setD_loan(int d_loan) {
        D_loan = d_loan;
    }

    public int getD_game() {
        return D_game;
    }

    public void setD_game(int d_game) {
        D_game = d_game;
    }

    public int getD_medical() {
        return D_medical;
    }

    public void setD_medical(int d_medical) {
        D_medical = d_medical;
    }

    public int getD_bigTravel() {
        return D_bigTravel;
    }

    public void setD_bigTravel(int d_bigTravel) {
        D_bigTravel = d_bigTravel;
    }

    public int getD_otherService() {
        return D_otherService;
    }

    public void setD_otherService(int d_otherService) {
        D_otherService = d_otherService;
    }

    public int getYid() {
        return Yid;
    }

    public void setYid(int yid) {
        Yid = yid;
    }
}
