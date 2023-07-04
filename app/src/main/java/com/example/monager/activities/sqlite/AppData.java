package com.example.monager.activities.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppData extends SQLiteOpenHelper {
    public static final String DB_Name = "Monager";
    public static final int version = 1 ;
    // constructor
    public AppData(@Nullable Context context) {
        super(context, DB_Name, null, version);
    }

    // tạo table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Usersql = "CREATE TABLE User (UserName text,UserSurname text,UserPass text,Salary int,TotalSave int,MSpendingLevel int,BonusMoney int,YourLoan int,SalTime int)";
        String Yearsql = "CREATE TABLE Year (Yid INTEGER PRIMARY KEY AUTOINCREMENT, Yname TEXT, YmoneyInTotal INTEGER, YmoneyOutTotal INTEGER)";
        String Monthsql ="CREATE TABLE Month (Mid INTEGER PRIMARY KEY AUTOINCREMENT, Mname TEXT, MmoneyIn INTEGER, MmoneyOut INTEGER, M_moving INTEGER, M_eat_drink INTEGER, M_shopping INTEGER, M_study INTEGER,M_Hrent INTEGER, M_loan INTEGER, M_game INTEGER, M_medical INTEGER, M_bigTravel INTEGER, M_otherService INTEGER, Yid INTEGER NOT NULL, FOREIGN KEY(Yid) REFERENCES Year(Yid))";
        String Daysql = "CREATE TABLE Day (Did INTEGER PRIMARY KEY AUTOINCREMENT, Dname text, DmoneyIn int, DmoneyOut int, D_moving int, D_eat_drink int, D_shopping int, D_study int,D_Hrent int, D_loan int, D_game int, D_medical int, D_bigTravel int, D_otherService int, Yid int NOT NULL, Mid int NOT NULL, FOREIGN KEY(Yid) REFERENCES Year(Yid), FOREIGN KEY(Mid) REFERENCES Month(Mid))";
        String Historysql ="CREATE TABLE History (HistoryID INTEGER PRIMARY KEY AUTOINCREMENT, HistoryName TEXT, Type TEXT, Value int, HistoryDate DATE,Note text, Yid INTEGER NOT NULL, Mid INTEGER NOT NULL, Did INTEGER NOT NULL, FOREIGN KEY(Yid) REFERENCES Year(Yid), FOREIGN KEY(Mid) REFERENCES Month(Mid), FOREIGN KEY(Did) REFERENCES Day(Did))";
        String GroupNumbersql ="CREATE TABLE GroupNumber (GNum_id INTEGER PRIMARY KEY AUTOINCREMENT, Gname TEXT, GNum INTEGER, DateCreated DATE)";
        String GroupMoneysql= "CREATE TABLE GroupMoney (G_personID INTEGER PRIMARY KEY AUTOINCREMENT, G_person_name TEXT, G_moving INTEGER, G_eat_drink INTEGER, G_shopping INTEGER, G_game INTEGER, G_bigtravel INTEGER, G_otherService INTEGER, GNum_id INTEGER NOT NULL, FOREIGN KEY(GNum_id) REFERENCES GroupNumber(GNum_id))";
        String GroupHistory ="CREATE TABLE GroupHistory (\n" +
                "    G_personID INTEGER,\n" +
                "    GNum_id INTEGER,\n" +
                "  \tG_type TEXT,\n" +
                "    G_money_stack INTEGER,\n" +
                "    G_note TEXT,\n" +
                "    TransDate DATE,\n" +
                "    FOREIGN KEY (G_personID) REFERENCES GroupMoney(G_personID),\n" +
                "    FOREIGN KEY (GNum_id) REFERENCES GroupNumber(GNum_id)\n" +
                ")";
        sqLiteDatabase.execSQL(Usersql);
        sqLiteDatabase.execSQL(Yearsql);
        sqLiteDatabase.execSQL(Monthsql);
        sqLiteDatabase.execSQL(Daysql);
        sqLiteDatabase.execSQL(Historysql);
        sqLiteDatabase.execSQL(GroupNumbersql);
        sqLiteDatabase.execSQL(GroupMoneysql);
        sqLiteDatabase.execSQL(GroupHistory);
    }
    // cập nhật/ nâng cấp csdl
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS User";
        String sql2 = "DROP TABLE IF EXISTS Year";
        String sql3 = "DROP TABLE IF EXISTS Month";
        String sql4 = "DROP TABLE IF EXISTS Day";
        String sql5 = "DROP TABLE IF EXISTS History";
        String sql6 = "DROP TABLE IF EXISTS GroupNumber";
        String sql7 = "DROP TABLE IF EXISTS GroupMoney";
        String sql8 = "DROP TABLE IF EXISTS GroupHistory";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
        sqLiteDatabase.execSQL(sql4);
        sqLiteDatabase.execSQL(sql5);
        sqLiteDatabase.execSQL(sql6);
        sqLiteDatabase.execSQL(sql7);
        sqLiteDatabase.execSQL(sql8);
        onCreate(sqLiteDatabase);
    }
}
