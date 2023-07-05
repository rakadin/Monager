package com.example.monager.activities.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monager.activities.models.Day;
import com.example.monager.activities.models.Month;

public class DayDAO {
    private SQLiteDatabase db;
    // khởi tạo trường Folder
    public DayDAO(Context context) { // phải truyền context vào
        AppData appData = new AppData(context); // khai bao de ket noi
        this.db = appData.getWritableDatabase();
    }
    /*
   thêm Month mới vào DB
   */
    public long insertDay(Day day)
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("Dname", day.getDname());
        values.put("DmoneyIn", day.getDmoneyIn());
        values.put("DmoneyOut", day.getDmoneyOut());
        values.put("D_moving", day.getD_moving());
        values.put("D_eat_drink", day.getD_eat_drink());
        values.put("D_shopping", day.getD_shopping());
        values.put("D_study", day.getD_study());
        values.put("D_Hrent", day.getD_Hrent());
        values.put("D_loan", day.getD_loan());
        values.put("D_game", day.getD_game());
        values.put("D_medical", day.getD_medical());
        values.put("D_bigTravel", day.getD_bigTravel());
        values.put("D_otherService", day.getD_otherService());
        values.put("Yid", day.getYid());
        values.put("Mid", day.getMid());
        return  db.insert("Day",null, values);// gửi value kia vào database
    }
    // check day exist
    public boolean checkDay(String dayS,int Mid,int Yid) {
        Cursor cursor = db.rawQuery("SELECT Dname FROM Day WHERE Dname = ? AND Mid =? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        if( cursor.moveToNext() == false)
        {
            return false;
        }
        else return true;
    }
    public int getDid(String dayS,int Mid,int Yid) {
        Cursor cursor = db.rawQuery("SELECT Did FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getDmoneyIn(String monthS,int Mid ,int Yid) {
        Cursor cursor = db.rawQuery("SELECT DmoneyIn FROM Day WHERE Dname = ? AND Mid = ? AND Yid =?", new String[]{monthS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getDmoneyOut(String monthS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT DmoneyOut FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{monthS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_moving(String monthS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_moving FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{monthS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_eat_drink(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_eat_drink FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_shopping(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_shopping FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_study(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_study FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_Hrent(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_Hrent FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_loan(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_loan FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_game(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_game FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_medical(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_medical FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_bigTravel(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_bigTravel FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getD_otherService(String dayS,int Mid, int Yid) {
        Cursor cursor = db.rawQuery("SELECT D_otherService FROM Day WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{dayS,String.valueOf(Mid),String.valueOf(Yid)});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public void updateDmoneyIn(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET DmoneyIn = DmoneyIn + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateDmoneyOut(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET DmoneyOut = DmoneyOut + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_moving(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_moving = D_moving + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_eat_drink(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_eat_drink = D_eat_drink + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_shopping(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_shopping = D_shopping + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_study(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_study = D_study + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_Hrent(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_Hrent = D_Hrent + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_loan(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_loan = D_loan + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_game(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_game = D_game + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_medical(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_medical = D_medical + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_bigTravel(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_bigTravel = D_bigTravel + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
    public void updateD_otherService(int money ,String dayS,int Mid, int Yid)
    {
        db.execSQL("UPDATE Day SET D_otherService = D_otherService + ? WHERE Dname = ? AND Mid = ? AND Yid = ?", new String[]{String.valueOf(money),dayS,String.valueOf(Mid),String.valueOf(Yid)});
    }
}
