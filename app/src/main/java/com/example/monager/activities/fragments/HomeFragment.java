package com.example.monager.activities.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.monager.R;
import com.example.monager.activities.sqlite.UserDAO;
import com.example.monager.activities.utils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UserDAO userDAO;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TextView butPer,butGr,nameText;
    public ImageButton movingBut,eatBut,shopBut,studyBut,rentBut,loanBut,gameBut,medicalBut,travelBut,otherBut;
    public com.example.monager.activities.utils utils = new utils();
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        userDAO = new UserDAO(view.getContext());
        getIDs(view);
        nameText.setText("Xin chào,"+userDAO.getUserName()+"🤘");
        newPersonTrans(view.getContext());//run function
        // Inflate the layout for this fragment
        return view;
    }
    // get iDS func
    public void getIDs(View view)
    {
        butPer = view.findViewById(R.id.new_tran);
        butGr = view.findViewById(R.id.new_grouptran);
        movingBut = view.findViewById(R.id.moving);
        eatBut = view.findViewById(R.id.eat);
        shopBut = view.findViewById(R.id.shopping);
        studyBut = view.findViewById(R.id.study);
        rentBut = view.findViewById(R.id.Hrent);
        loanBut = view.findViewById(R.id.money);
        gameBut = view.findViewById(R.id.game);
        medicalBut = view.findViewById(R.id.medical);
        travelBut = view.findViewById(R.id.travel);
        otherBut = view.findViewById(R.id.other);
        nameText = view.findViewById(R.id.nameText);
    }
    public void newPersonTrans(Context context)
    {
        butPer.setOnClickListener(view -> {
            Long big = 100000000000L;
            Toast.makeText(context,"click "+utils.formatMoney(big),Toast.LENGTH_LONG).show();
        });
    }
}