package com.example.monager.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monager.R;

public class Start_up_activity extends AppCompatActivity {
    //ID của theme mà Activity sử dụng
    utils Utils;
    int themeIdcurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        //Đọc ID theme đã lưu, nếu chưa lưu thì dùng R.style.MyAppTheme
        SharedPreferences locationpref = getApplicationContext()
                .getSharedPreferences("MainActivity", MODE_PRIVATE);
        themeIdcurrent = locationpref.getInt("themeid",R.style.DarkTheme);
        setTheme(R.style.DarkTheme);
        //Thiết lập theme cho Activity
        setContentView(R.layout.activity_start_up);
        getSupportActionBar().hide();
        // next activity -> auto
        Utils.delay(50, () -> {
            Intent intent = new Intent();
            intent.setClass(this, HomePage_activity.class);
            intent.putExtra("themeid",R.style.DarkTheme);
            startActivity(intent);
        });
    }
    // use to change to dark mode or light mode
    public void changetheme()
    {
        //Chuyển đổi theme
//        themeIdcurrent = themeIdcurrent == R.style.MyAppTheme ? R.style.AppTheme : R.style.MyAppTheme;

        //Lưu lại theme ID
        SharedPreferences locationpref = getApplicationContext()
                .getSharedPreferences("MainActivity", MODE_PRIVATE);
        SharedPreferences.Editor spedit = locationpref.edit();
        spedit.putInt("themeid", themeIdcurrent);
        spedit.apply();

        //Tạo lại Activity để áp dụng theme mởi đổi
        recreate();
    }
}