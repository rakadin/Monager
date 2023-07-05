package com.example.monager.activities.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monager.activities.models.Month;
import com.example.monager.activities.models.Year;

public class MonthDAO {
    private SQLiteDatabase db;
    // khởi tạo trường Folder
    public MonthDAO(Context context) { // phải truyền context vào
        AppData appData = new AppData(context); // khai bao de ket noi
        this.db = appData.getWritableDatabase();
    }
    /*
   thêm Month mới vào DB
   */
    public long insertMonth(Month month)
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("Mname", month.getMname());
        values.put("MmoneyIn", month.getMmoneyIn());
        values.put("MmoneyOut", month.getMmoneyOut());
        values.put("M_moving", month.getM_moving());
        values.put("M_eat_drink", month.getM_eat_drink());
        values.put("M_shopping", month.getM_shopping());
        values.put("M_study", month.getM_study());
        values.put("M_Hrent", month.getM_Hrent());
        values.put("M_loan", month.getM_loan());
        values.put("M_game", month.getM_game());
        values.put("M_medical", month.getM_medical());
        values.put("M_bigTravel", month.getM_bigTravel());
        values.put("M_otherService", month.getM_otherService());
        values.put("Yid", month.getYid());

        return  db.insert("Month",null, values);// gửi value kia vào database
    }
    // check month exist
    public boolean checkMonth(String monthS,int Yid) {
        Cursor cursor = db.rawQuery("SELECT Mname FROM Month WHERE Mname =? AND Yid =?", new String[]{monthS,String.valueOf(Yid)});
        if( cursor.moveToNext() == false)
        {
            return false;
        }
        else return true;
    }
    public int getMid(String monthS,int Yid) {
        Cursor cursor = db.rawQuery("SELECT Mid FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getMmoneyIn(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT MmoneyIn FROM Month WHERE Mname = ? AND Yid =?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getMmoneyOut(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT MmoneyOut FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_moving(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_moving FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_eat_drink(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_eat_drink FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_shopping(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_shopping FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_study(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_study FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_Hrent(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_Hrent FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_loan(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_loan FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_game(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_game FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_medical(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_medical FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_bigTravel(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_bigTravel FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getM_otherService(String monthS, int Yid) {
        Cursor cursor = db.rawQuery("SELECT M_otherService FROM Month WHERE Mname = ? AND Yid = ?", new String[]{monthS,String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public void updateMmoneyIn(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET MmoneyIn = MmoneyIn + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateMmoneyOut(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET MmoneyOut = MmoneyOut + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_moving(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_moving = M_moving + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_eat_drink(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_eat_drink = M_eat_drink + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }

    public void updateM_study(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_study = M_study + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_Hrent(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_Hrent = M_Hrent + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_loan(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_loan = M_loan + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_game(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_game = M_game + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_medical(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_medical = M_medical + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_bigTravel(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_bigTravel = M_bigTravel + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void updateM_otherService(int money ,String monthS, int Yid)
    {
        db.execSQL("UPDATE Month SET M_otherService = M_otherService + ? WHERE Mname = ? AND Yid = ?", new String[]{String.valueOf(money),monthS,String.valueOf(Yid)});
    }
    public void delete(String monthS)
    {
        db.execSQL("DELETE FROM Month WHERE Mname = ? ", new String[]{monthS});
    }
}
