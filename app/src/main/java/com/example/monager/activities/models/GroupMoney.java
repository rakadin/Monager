package com.example.monager.activities.models;

public class GroupMoney {
    private int G_personID;
    private String G_person_name;
    private int G_moving;
    private int G_eat_drink;
    private int G_shopping;
    private int G_game;
    private int G_bigtravel;
    private int G_otherService;
    private int GNum_id;

    public GroupMoney(int g_personID, String g_person_name, int g_moving, int g_eat_drink, int g_shopping, int g_game, int g_bigtravel, int g_otherService, int GNum_id) {
        G_personID = g_personID;
        G_person_name = g_person_name;
        G_moving = g_moving;
        G_eat_drink = g_eat_drink;
        G_shopping = g_shopping;
        G_game = g_game;
        G_bigtravel = g_bigtravel;
        G_otherService = g_otherService;
        this.GNum_id = GNum_id;
    }

    public int getG_personID() {
        return G_personID;
    }

    public void setG_personID(int g_personID) {
        G_personID = g_personID;
    }

    public String getG_person_name() {
        return G_person_name;
    }

    public void setG_person_name(String g_person_name) {
        G_person_name = g_person_name;
    }

    public int getG_moving() {
        return G_moving;
    }

    public void setG_moving(int g_moving) {
        G_moving = g_moving;
    }

    public int getG_eat_drink() {
        return G_eat_drink;
    }

    public void setG_eat_drink(int g_eat_drink) {
        G_eat_drink = g_eat_drink;
    }

    public int getG_shopping() {
        return G_shopping;
    }

    public void setG_shopping(int g_shopping) {
        G_shopping = g_shopping;
    }

    public int getG_game() {
        return G_game;
    }

    public void setG_game(int g_game) {
        G_game = g_game;
    }

    public int getG_bigtravel() {
        return G_bigtravel;
    }

    public void setG_bigtravel(int g_bigtravel) {
        G_bigtravel = g_bigtravel;
    }

    public int getG_otherService() {
        return G_otherService;
    }

    public void setG_otherService(int g_otherService) {
        G_otherService = g_otherService;
    }

    public int getGNum_id() {
        return GNum_id;
    }

    public void setGNum_id(int GNum_id) {
        this.GNum_id = GNum_id;
    }
}
