package com.example.monager.activities.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monager.activities.models.User;

import java.util.List;

public class UserDAO {
    private SQLiteDatabase db;
    // khởi tạo trường Folder
    public UserDAO(Context context) { // phải truyền context vào
        AppData appData = new AppData(context); // khai bao de ket noi
        this.db = appData.getWritableDatabase();
    }
    /*
       thêm User mới nào DB
        */
    public long insertUser(User user)
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("UserName", user.getUserName());
        values.put("UserSurname", user.getUserSurname());
        values.put("UserPass", user.getUserPass());
        values.put("Salary", user.getSalary());
        values.put("TotalSave", user.getTotalSave());
        values.put("MSpendingLevel", user.getMSpendingLevel());
        values.put("BonusMoney", user.getBonusMoney());
        values.put("YourLoan", user.getYourLoan());
        values.put("SalTime", user.getSalTime());
        return  db.insert("User",null, values);// gửi value kia vào database
    }
    // get select from User
    public String getUserName() {
        Cursor cursor = db.rawQuery("SELECT UserName FROM User", null);
        String userName = null;
        cursor.moveToNext();
        userName = cursor.getString(0);
        cursor.close();
        return userName;
    }
    // get select from User
    public String getUserSurname() {
        Cursor cursor = db.rawQuery("SELECT UserSurname FROM User", null);
        String userSurname = null;
        cursor.moveToNext();
        userSurname = cursor.getString(0);
        cursor.close();
        return userSurname;
    }
    public String getUserPass() {
        Cursor cursor = db.rawQuery("SELECT UserPass FROM User", null);
        String userPass = null;
        cursor.moveToNext();
        userPass = cursor.getString(0);
        cursor.close();
        return userPass;
    }
    public int getSalary() {
        Cursor cursor = db.rawQuery("SELECT Salary FROM User", null);
        int salary = 0;
        cursor.moveToNext();
        salary = cursor.getInt(0);
        cursor.close();
        return salary;
    }
    public int getTotalSave() {
        Cursor cursor = db.rawQuery("SELECT TotalSave FROM User", null);
        int tems = 0;
        cursor.moveToNext();
        tems = cursor.getInt(0);
        cursor.close();
        return tems;
    }
    public int getMSpendingLevel() {
        Cursor cursor = db.rawQuery("SELECT MSpendingLevel FROM User", null);
        int tems = 0;
        cursor.moveToNext();
        tems = cursor.getInt(0);
        cursor.close();
        return tems;
    }
    public int getYourLoan() {
        Cursor cursor = db.rawQuery("SELECT YourLoan FROM User", null);
        int tems = 0;
        cursor.moveToNext();
        tems = cursor.getInt(0);
        cursor.close();
        return tems;
    }
    public int getSaltime() {
        Cursor cursor = db.rawQuery("SELECT SalTime FROM User", null);
        int tems = 0;
        cursor.moveToNext();
        tems = cursor.getInt(0);
        cursor.close();
        return tems;
    }
    public void updateUserName(String string)
    {
        db.execSQL("UPDATE User SET UserName = ?", new String[]{string});
    }
    public void updateUserSurname(String string)
    {
        db.execSQL("UPDATE User SET UserSurname = ?", new String[]{string});
    }
    public void updateUserPass(String string)
    {
        db.execSQL("UPDATE User SET UserPass = ?", new String[]{string});
    }
    public void updateSalary(int number)
    {
        db.execSQL("UPDATE User SET Salary = ? ", new String[]{String.valueOf(number)});
    }
    public void updateTotalSave(int number)
    {
        db.execSQL("UPDATE User SET TotalSave = TotalSave + ? ", new String[]{String.valueOf(number)});
    }
    public void updateMSpendingLevel(int number)
    {
        db.execSQL("UPDATE User SET MSpendingLevel = ? ", new String[]{String.valueOf(number)});
    }
    public void updateSalTime(int number)
    {
        db.execSQL("UPDATE User SET SalTime = ? ", new String[]{String.valueOf(number)});
    }
//    /*
//set things by id
//*/
//    public void updateByID(String name,String content,int id)
//    {
//        db.execSQL("UPDATE Item SET Iname = ?,Icontent = ? where IiD = ?", new String[]{name,content,String.valueOf(id)});
//    }
//public void getItemsFromFID(int Fid, List<Item> itemDataList)
//{
//    Cursor cursor =  db.rawQuery("SELECT * FROM Item where Fid = ?", new String[]{String.valueOf(Fid)} );
//
//    for(int i =0;i<cursor.getCount();i++)
//    {
//        Item item = new Item();
//        cursor.moveToNext();
//        item.setItemID(cursor.getInt(0));
//        item.setFolderID(cursor.getInt(1));
//        item.setItemName(cursor.getString(2));
//        item.setItemContent(cursor.getString(3));
//        itemDataList.add(item);
//    }
//}
}
