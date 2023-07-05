package com.example.monager.activities.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.monager.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        Switch sw1,sw2,sw3,sw4;
        // get ids
        sw1 = view.findViewById(R.id.switch1);
        sw2 = view.findViewById(R.id.switch2);
        sw3 = view.findViewById(R.id.switch3);
        sw4 = view.findViewById(R.id.switch4);
        changeThemeSwitch(sw1,view);

        // Inflate the layout for this fragment
        return view;
    }
    // chức năng thay đổi theme sáng tối
    public void changeThemeSwitch(Switch sw1,View view)
    {
        int themeIdcurrent;
        ////Đọc ID theme đã lưu, nếu chưa lưu thì dùng R.style.MyAppTheme
        SharedPreferences locationpref = view.getContext()
                .getSharedPreferences("GetTheme", MODE_PRIVATE);
        themeIdcurrent = locationpref.getInt("themeid",R.style.LightTheme);
        if(themeIdcurrent== R.style.DarkTheme){
            sw1.toggle();
        }
        sw1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // turn switch
                    Toast.makeText(view.getContext(),"chuyển sw",Toast.LENGTH_SHORT).show();
                    if ( themeIdcurrent == R.style.DarkTheme) {
                        //Lưu lại theme ID
                        SharedPreferences locationpref = view.getContext()
                                .getSharedPreferences("GetTheme", MODE_PRIVATE);
                        SharedPreferences.Editor spedit = locationpref.edit();
                        spedit.putInt("themeid", R.style.LightTheme);
                        spedit.apply();
                        // Khởi chạy lại Activity
                        Intent intent = requireActivity().getIntent();
                        requireActivity().finish();
                        startActivity(intent);
                    }
                    if ( themeIdcurrent == R.style.LightTheme)
                    {
                        //Lưu lại theme ID
                        SharedPreferences locationpref = view.getContext()
                                .getSharedPreferences("GetTheme", MODE_PRIVATE);
                        SharedPreferences.Editor spedit = locationpref.edit();
                        spedit.putInt("themeid", R.style.DarkTheme);
                        spedit.apply();
                        // Khởi chạy lại Activity
                        Intent intent = requireActivity().getIntent();
                        requireActivity().finish();
                        startActivity(intent);
                    }
                    }
            });

    }
}