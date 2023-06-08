package com.example.monager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.monager.R;

public class HomePage_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_page);
    }
}