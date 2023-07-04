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
