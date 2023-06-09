package com.example.monager.activities;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.monager.R;
import com.example.monager.activities.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage_activity extends AppCompatActivity {
    private ActionBar toolbar;
    private TextView navname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get theme id from previous
        Bundle ids = getIntent().getExtras();
        int id = ids.getInt("themeid");
        setTheme(id);
        setContentView(R.layout.activity_home_page);
        // get ids
        navname = findViewById(R.id.nav_name);
        toolbar = getSupportActionBar();
        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        changeNav("Trang chủ");
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_chart:
                    changeNav("Biểu đồ");

                    return true;
                case R.id.navigation_home:
                    changeNav("Trang chủ");

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