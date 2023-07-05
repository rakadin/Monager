package com.example.monager.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monager.R;

public class Start_up_activity extends AppCompatActivity {
    //ID của theme mà Activity sử dụng
    utils Utils;
    int themeIdcurrent;
    int firsttime;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////Đọc ID theme đã lưu, nếu chưa lưu thì dùng R.style.MyAppTheme
        SharedPreferences locationpref = getApplicationContext()
                .getSharedPreferences("GetTheme", MODE_PRIVATE);
        themeIdcurrent = locationpref.getInt("themeid", R.style.LightTheme);
        setTheme(themeIdcurrent);
        // xem có phải lần đầu người dùng sử dụng ứng dụng không
        SharedPreferences locationpref2 = getApplicationContext()
                .getSharedPreferences("MainActivity", MODE_PRIVATE);
        firsttime = locationpref2.getInt("firsttime", 0);
        //Thiết lập theme cho Activity
        setContentView(R.layout.activity_start_up);
        dialog = new Dialog(Start_up_activity.this);
        getSupportActionBar().hide();
        // next activity -> auto
        Utils.delay(50, () -> {
            if (firsttime == 0)// là người dùng mới
            {
                // su kien alert dialog
                AlertDialog.Builder b = new AlertDialog.Builder(Start_up_activity.this,R.style.CustomAlertDialogStyle);
                b.setTitle("Lấy thông tin người dùng");
                b.setIcon(R.drawable.faq_icon);
                b.setMessage("Bạn hiện đang là người dùng mới nên hãy dành một chút thời gian để điền một số thông tin cần thiết cho ứng dụng nhé 💖");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setClass(Start_up_activity.this, Get_User_Info_activity.class);
                        startActivity(intent);
                    }
                });
                b.create().show();// show dialog
            } else {
                Intent intent = new Intent();
                intent.setClass(this, HomePage_activity.class);
                startActivity(intent);
                //
            }
        });

    }
}
