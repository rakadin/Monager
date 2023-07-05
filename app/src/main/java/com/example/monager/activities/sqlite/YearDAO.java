package com.example.monager.activities.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monager.activities.models.User;
import com.example.monager.activities.models.Year;

public class YearDAO {
    private SQLiteDatabase db;
    // khởi tạo trường Folder
    public YearDAO(Context context) { // phải truyền context vào
        AppData appData = new AppData(context); // khai bao de ket noi
        this.db = appData.getWritableDatabase();
    }
    /*
    thêm Year mới vào DB
    */
    public long insertYear(Year year)
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("Yname", year.getYname());
        values.put("YmoneyInTotal", year.getYmoneyInTotal());
        values.put("YmoneyOutTotal", year.getYmoneyOutTotal());

        return  db.insert("Year",null, values);// gửi value kia vào database
    }
    // check year exist
    public boolean checkYear(String yearS) {
        Cursor cursor = db.rawQuery("SELECT Yname FROM Year WHERE Yname = ?", new String[]{yearS});
        if( cursor.moveToNext() == false)
        {
            return false;
        }
        else return true;

    }
    public int getYid(String yearS) {
        Cursor cursor = db.rawQuery("SELECT Yid FROM Year WHERE Yname = ?", new String[]{yearS});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getYmoneyInTotal(String yearS) {
        Cursor cursor = db.rawQuery("SELECT YmoneyInTotal FROM Year WHERE Yname = ?", new String[]{yearS});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public int getYmoneyOutTotal(String yearS) {
        Cursor cursor = db.rawQuery("SELECT YmoneyOutTotal FROM Year WHERE Yname = ?", new String[]{yearS});
        int money = 0;
        cursor.moveToNext();
        money= cursor.getInt(0);
        cursor.close();
        return money;
    }
    public void updateYmoneyInTotal(int money ,String string)
    {
        db.execSQL("UPDATE Year SET YmoneyInTotal = YmoneyInTotal + ? WHERE Yname =?", new String[]{String.valueOf(money),string});
    }
    public void updateYmoneyOutTotal(int money ,String string)
    {
        db.execSQL("UPDATE Year SET YmoneyOutTotal = YmoneyOutTotal + ? WHERE Yname =?", new String[]{String.valueOf(money),string});
    }
}
