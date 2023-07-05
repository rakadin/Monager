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

import com.bumptech.glide.util.Util;
import com.example.monager.R;
import com.example.monager.activities.models.Day;
import com.example.monager.activities.models.History;
import com.example.monager.activities.models.Month;
import com.example.monager.activities.models.Year;
import com.example.monager.activities.sqlite.DayDAO;
import com.example.monager.activities.sqlite.HistoryDAO;
import com.example.monager.activities.sqlite.MonthDAO;
import com.example.monager.activities.sqlite.UserDAO;
import com.example.monager.activities.sqlite.YearDAO;
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
    private YearDAO yearDAO;
    private MonthDAO monthDAO;
    private DayDAO dayDAO;
    private HistoryDAO historyDAO;
    private utils util;
    private int y,m,d;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TextView butPer,butGr,nameText;
    public ImageButton movingBut,eatBut,shopBut,studyBut,rentBut,loanBut,gameBut,medicalBut,travelBut,otherBut;
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
        yearDAO = new YearDAO(view.getContext());
        monthDAO = new MonthDAO(view.getContext());
        dayDAO = new DayDAO(view.getContext());
        historyDAO = new HistoryDAO(view.getContext());
        util = new utils();
        y = util.getYear();//get year
        m = util.getMonth();//get month
        d = util.getDay();//get day
        getIDs(view);
        nameText.setText("Xin chào,"+userDAO.getUserName()+"🤘");

         //check if data row about year month day exist or not
        if((yearDAO.checkYear(String.valueOf(y)) == false))
        {
            Year temyear = new Year();
            temyear.setYname(String.valueOf(y));
            temyear.setYmoneyInTotal(0);
            temyear.setYmoneyOutTotal(0);
            yearDAO.insertYear(temyear);

        }
        else
        {
//            String check = String.valueOf(yearDAO.checkYear(String.valueOf(y)));
//            Toast.makeText(view.getContext(),"click "+check+y+" "+yearDAO.getYid(String.valueOf(y)),Toast.LENGTH_LONG).show();
        }
        if((monthDAO.checkMonth(String.valueOf(m),yearDAO.getYid(String.valueOf(y))) == false))
        {
               // monthDAO.delete(String.valueOf(m));
            Month temmonth = new Month();
            temmonth.setMname(String.valueOf(m));
            temmonth.setMmoneyIn(0);
            temmonth.setMmoneyOut(0);
            temmonth.setM_moving(0);
            temmonth.setM_eat_drink(0);
            temmonth.setM_shopping(0);
            temmonth.setM_study(0);
            temmonth.setM_Hrent(0);;
            temmonth.setM_loan(0);;
            temmonth.setM_game(0);;
            temmonth.setM_medical(0);
            temmonth.setM_bigTravel(0);
            temmonth.setM_otherService(0);
            temmonth.setYid(yearDAO.getYid(String.valueOf(y)));
            monthDAO.insertMonth(temmonth);
        }
        else
        {
//            String check = String.valueOf(monthDAO.checkMonth(String.valueOf(m), yearDAO.getYid(String.valueOf(y))));
//            Toast.makeText(view.getContext(),"click "+m+check+" ",Toast.LENGTH_LONG).show();
        }
        // check if day row today exist
        if((dayDAO.checkDay(String.valueOf(d),monthDAO.getMid(String.valueOf(m),yearDAO.getYid(String.valueOf(y))),yearDAO.getYid(String.valueOf(y)))) == false)
        {
            Day temmonth = new Day();
            temmonth.setDname(String.valueOf(d));
            temmonth.setDmoneyIn(0);
            temmonth.setDmoneyOut(0);
            temmonth.setD_moving(0);
            temmonth.setD_eat_drink(0);
            temmonth.setD_shopping(0);
            temmonth.setD_study(0);
            temmonth.setD_Hrent(0);;
            temmonth.setD_loan(0);;
            temmonth.setD_game(0);;
            temmonth.setD_medical(0);
            temmonth.setD_bigTravel(0);
            temmonth.setD_otherService(0);
            temmonth.setYid(yearDAO.getYid(String.valueOf(y)));
            temmonth.setMid(monthDAO.getMid(String.valueOf(m),yearDAO.getYid(String.valueOf(y))));
            dayDAO.insertDay(temmonth);
        }
        else
        {
//            String check = String.valueOf(dayDAO.checkDay(String.valueOf(d),monthDAO.getMid(String.valueOf(m),yearDAO.getYid(String.valueOf(y))),yearDAO.getYid(String.valueOf(y))));
//            Toast.makeText(view.getContext(),"click "+d+check+" ",Toast.LENGTH_LONG).show();
        }
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
            int big = 100000000;
            Toast.makeText(context,"click "+util.getMonth(),Toast.LENGTH_LONG).show();
        });
    }
}