package com.example.monager.activities.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monager.activities.models.Day;
import com.example.monager.activities.models.History;
import com.example.monager.activities.models.HistoryItem;

import java.util.List;

public class HistoryDAO {
    private SQLiteDatabase db;
    // khởi tạo trường Folder
    public HistoryDAO(Context context) { // phải truyền context vào
        AppData appData = new AppData(context); // khai bao de ket noi
        this.db = appData.getWritableDatabase();
    }
    /*
   thêm History mới vào DB
   */
    public long insertHistory(History history)
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("HistoryName", history.getHistoryName());
        values.put("Type", history.getType());
        values.put("Value", history.getValue());
        values.put("HistoryDate", history.getHistoryDate());
        values.put("Note", history.getNote());
        values.put("YDid", history.getDid());
        values.put("Yid", history.getYid());
        values.put("Mid", history.getMid());
        return  db.insert("History",null, values);// gửi value kia vào database
    }
    public void getData(int Did,int Mid,int Yid, List<HistoryItem> itemDataList) {
        Cursor cursor = db.rawQuery("SELECT Type, Value, HistoryDate, Note FROM History WHERE Did = ? AND Mid = ? AND Yid = ? ", new String[]{String.valueOf(Did),String.valueOf(Mid),String.valueOf(Yid)});
        for(int i =0;i<cursor.getCount();i++)
        {
            HistoryItem item = new HistoryItem();
            cursor.moveToNext();
            item.setType(cursor.getString(0));
            item.setValue(cursor.getInt(1));
            item.setHistoryDate(cursor.getString(2));
            item.setNote(cursor.getString(3));
            itemDataList.add(item);
        }
    }
}
