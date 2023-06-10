package com.example.monager.activities;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.monager.R;
import com.example.monager.activities.fragments.ChartFragment;
import com.example.monager.activities.fragments.HomeFragment;
import com.example.monager.activities.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage_activity extends AppCompatActivity {
    private ActionBar toolbar;
    private TextView navname;
    int themeIdcurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////Đọc ID theme đã lưu, nếu chưa lưu thì dùng R.style.MyAppTheme
        SharedPreferences locationpref = getApplicationContext()
                .getSharedPreferences("MainActivity", MODE_PRIVATE);
        themeIdcurrent = locationpref.getInt("themeid",R.style.LightTheme);
        setTheme(themeIdcurrent);
        setContentView(R.layout.activity_home_page);
        // get ids
        navname = findViewById(R.id.nav_name);
        toolbar = getSupportActionBar();
        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // load first fragment
        changeNav("Trang chủ");
        Fragment fragment;
        fragment = new HomeFragment();
        loadFragment(fragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_chart:
                    changeNav("Biểu đồ");
                    fragment = new ChartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_home:
                    changeNav("Trang chủ");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_setting:
                    changeNav("Cài đặt");
                    fragment = new SettingFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    // change top name by navigation
    private void changeNav(String name)
    {
        navname.setText(name);
    }

    // load fragments
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}