package com.example.monager.activities.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monager.activities.models.Day;
import com.example.monager.activities.models.History;
import com.example.monager.activities.models.HistoryItem;
import com.example.monager.activities.models.HistoryLoan;

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
    public long insertHistory(History history) {
        ContentValues values = new ContentValues();
        values.put("HistoryName", history.getHistoryName());
        values.put("Type", history.getType());
        values.put("Value", history.getValue());
        values.put("HistoryDate", history.getHistoryDate()); // Chỉnh sửa thành "date('now', 'localtime')"
        values.put("Note", history.getNote());
        values.put("Did", history.getDid());
        values.put("Yid", history.getYid());
        values.put("Mid", history.getMid());
        return db.insert("History", null, values);
    }
    public void getData(int Did, int Mid, int Yid, List<HistoryItem> itemDataList) {
        Cursor cursor = db.rawQuery("SELECT Type, Value, HistoryDate, Note FROM History WHERE Did = ? AND Mid = ? AND Yid = ? ORDER BY HistoryID DESC LIMIT 10 ",
                new String[]{String.valueOf(Did), String.valueOf(Mid), String.valueOf(Yid)});
        for (int i = 0; i < cursor.getCount(); i++) {
            HistoryItem item = new HistoryItem();
            cursor.moveToNext();
            item.setType(cursor.getString(0));
            item.setValue(cursor.getInt(1));
            item.setHistoryDate(cursor.getString(2));
            item.setNote(cursor.getString(3));
            itemDataList.add(item);
        }
    }
    public void getLoanData( List<HistoryLoan> itemDataList) {
        Cursor cursor = db.rawQuery("SELECT Value, HistoryDate, Note,HistoryID FROM History WHERE Type = ? AND Note <> ? ORDER BY HistoryID DESC",
                new String[]{"Cho vay","Đã trả"});
        for (int i = 0; i < cursor.getCount(); i++) {
            HistoryLoan item = new HistoryLoan();
            cursor.moveToNext();
            item.setValue(cursor.getInt(0));
            item.setLoanDate(cursor.getString(1));
            item.setLoanNote(cursor.getString(2));
            item.setHLoanID(cursor.getInt(3));
            itemDataList.add(item);
        }
    }
    public boolean checkData(int Did,int Mid,int Yid) {
        boolean tem = false;
        Cursor cursor = db.rawQuery("SELECT Type, Value, HistoryDate, Note FROM History WHERE Did = ? AND Mid = ? AND Yid = ? ", new String[]{String.valueOf(Did),String.valueOf(Mid),String.valueOf(Yid)});
        if(cursor.moveToNext() == true)
        {
            tem = true;
        }
        return tem;
    }
    public int getYidFromHistory(int Hid) {
        Cursor cursor = db.rawQuery("SELECT Yid FROM History WHERE HistoryID = ?" , new String[]{String.valueOf(Hid)});
        int tems = 0;
        cursor.moveToNext();
        tems = cursor.getInt(0);
        cursor.close();
        return tems;
    }
    public int getMidFromHistory(int Hid) {
        Cursor cursor = db.rawQuery("SELECT Mid FROM History WHERE HistoryID = ?" , new String[]{String.valueOf(Hid)});
        int tems = 0;
        cursor.moveToNext();
        tems = cursor.getInt(0);
        cursor.close();
        return tems;
    }
    public int getDidFromHistory(int Hid) {
        Cursor cursor = db.rawQuery("SELECT Did FROM History WHERE HistoryID = ?" , new String[]{String.valueOf(Hid)});
        int tems = 0;
        cursor.moveToNext();
        tems = cursor.getInt(0);
        cursor.close();
        return tems;
    }
    public void updateHNote(int Hid,String noteS)
    {
        db.execSQL("UPDATE History SET Note = ? WHERE HistoryID = ?", new String[]{noteS,String.valueOf(Hid)});
    }
}
