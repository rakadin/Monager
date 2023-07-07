package com.example.monager.activities.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.monager.R;
import com.example.monager.activities.Get_User_Info_activity;
import com.example.monager.activities.Start_up_activity;
import com.example.monager.activities.adapters.HistoryItemAdapter;
import com.example.monager.activities.adapters.HistoryLoanAdapter;
import com.example.monager.activities.models.Day;
import com.example.monager.activities.models.History;
import com.example.monager.activities.models.HistoryItem;
import com.example.monager.activities.models.HistoryLoan;
import com.example.monager.activities.models.Month;
import com.example.monager.activities.models.Year;
import com.example.monager.activities.sqlite.DayDAO;
import com.example.monager.activities.sqlite.HistoryDAO;
import com.example.monager.activities.sqlite.MonthDAO;
import com.example.monager.activities.sqlite.UserDAO;
import com.example.monager.activities.sqlite.YearDAO;
import com.example.monager.activities.utils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


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
    private Dialog dialog;
    private HistoryItemAdapter adapter;
    private HistoryLoanAdapter adapterLoan;

    private List<HistoryItem> data;
    // TODO: Rename and change types of parameters
    private ListView historyItemlv,loanLV;
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
        dialog = new Dialog(view.getContext());
        y = util.getYear();//get year
        m = util.getMonth();//get month
        d = util.getDay();//get day
        getIDs(view);
        nameText.setText("Xin chào,"+userDAO.getUserName()+"🤴");
        insertNewRows(view);// thêm row mới trước khi xử lý thêm dữ liệu - sang tháng mới,ngày mới năm mới thì thêm hàng data mới vào
        newPersonTrans(view.getContext());
        // show history
        int Yid = yearDAO.getYid(String.valueOf(y));
        int Mid = monthDAO.getMid(String.valueOf(m),Yid);
        int Did = dayDAO.getDid(String.valueOf(d),Mid,Yid);
        HistoryItem historyItem = new HistoryItem();
        data = new ArrayList<>();
        historyDAO.getData(Did,Mid,Yid,data);
        adapter = new HistoryItemAdapter(view.getContext(), R.layout.item_history_list,data);
        historyItemlv.setAdapter(adapter);
        butGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(view.getContext(),"Nợ " + userDAO.getYourLoan() ,Toast.LENGTH_LONG).show();

            }
        });


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
        historyItemlv = view.findViewById(R.id.recent_acitivity);

    }
    public void newPersonTrans(Context context)
    {
        butPer.setOnClickListener(view -> {
//            openPersonChooseDialog(dialog);
            // su kien alert dialog
            AlertDialog.Builder b = new AlertDialog.Builder(context,R.style.CustomAlertDialogStyle);
            b.setTitle("Chọn loại giao dịch");
            b.setIcon(R.drawable.faq_icon);
            b.setMessage("Bạn muốn thêm TIỀN TIẾT KIỆM hay là TIÊU TIỀN?");
            b.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            b.setNegativeButton("Thêm tiền", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // su kien alert dialog
                    AlertDialog.Builder b = new AlertDialog.Builder(context,R.style.CustomAlertDialogStyle);
                    b.setTitle("Chọn loại giao dịch");
                    b.setIcon(R.drawable.faq_icon);
                    b.setMessage("Bạn muốn thêm TIỀN KIẾM ĐƯỢC hay là XỬ LÝ NỢ CŨ?");
                    b.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    b.setNegativeButton("Xử lý nợ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog.setContentView(R.layout.choose_person_loan_dialog);
                            TextView textView = dialog.findViewById(R.id.totalloanText);
                            Button cancel = dialog.findViewById(R.id.cancelBut);
                            loanLV = dialog.findViewById(R.id.loanLV);
                            List<HistoryLoan> data2;
                            data2 = new ArrayList<>();
                            historyDAO.getLoanData(data2);
                            adapterLoan = new HistoryLoanAdapter(data,textView,getContext(), R.layout.loan_history_list,data2);
                            loanLV.setAdapter(adapterLoan);
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.cancel();
                                    adapter.notifyDataSetChanged();
                                }
                            });
                            dialog.show();
                        }
                    });
                    b.setPositiveButton("Tiền kiếm được", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog.setContentView(R.layout.set_person_trans_dialog);
                            TextView top = dialog.findViewById(R.id.afterChooseTextTop);
                            TextInputEditText money = dialog.findViewById(R.id.add_person_mon);
                            TextInputEditText note = dialog.findViewById(R.id.add_person_note);
                            Button cancel,add;
                            cancel = dialog.findViewById(R.id.cancelaBut);
                            add = dialog.findViewById(R.id.acceptBut);
                            top.setText("Thêm giao dịch");
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                            add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String s1 = money.getText().toString().trim();
                                    String s2 = note.getText().toString().trim();
                                    String typeS = "Thêm tiền";
                                    // xem nhập vào thỏa mãn không
                                    if(s2.equals("") || s1.equals(""))
                                    {
                                        Toast.makeText(view.getContext(),"Bạn cần nhập hết các trường thông tin!",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        int money1 = Integer.valueOf(s1);
                                        if(money1 == 0)
                                        {
                                            Toast.makeText(view.getContext(),"Số tiền bạn vừa nhập không hợp lý!",Toast.LENGTH_LONG).show();
                                        }
                                        else // tất cả đều hợp lý
                                        {
                                            int TotalSave = userDAO.getTotalSave();
                                            int After = TotalSave + money1;
                                            String saveS = util.formatMoney(After);
                                            // su kien alert dialog
                                            AlertDialog.Builder b = new AlertDialog.Builder(view.getContext(),R.style.CustomAlertDialogStyle);
                                            b.setTitle("Chấp nhận thêm mới?");
                                            b.setIcon(R.drawable.faq_icon);
                                            b.setMessage("Bạn muốn thêm giao dịch "+"'"+typeS+"' với số tiền "+util.formatMoney(money1)+" VND kèm ghi chú: '"+s2 + "' đúng chứ ?\nTiền tiết kiệm sẽ là: "+saveS+" VNĐ");
                                            b.setNegativeButton("Sửa lại", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            });
                                            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    int Yid = yearDAO.getYid(String.valueOf(y));
                                                    int Mid = monthDAO.getMid(String.valueOf(m),Yid);
                                                    int Did = dayDAO.getDid(String.valueOf(d),Mid,Yid);
                                                    History history = new History();
                                                    history.setHistoryName(d+"/"+m+"/"+y);
                                                    history.setType(typeS);
                                                    history.setValue(money1);
                                                    history.setNote(s2);
                                                    history.setHistoryDate(util.getDate());
                                                    history.setDid(Did);
                                                    history.setMid(Mid);
                                                    history.setYid(Yid);
                                                    HistoryItem item = new HistoryItem();
                                                    // Tạo một mục mới
                                                    HistoryItem newItem = new HistoryItem();
                                                    newItem.setValue(money1);
                                                    newItem.setNote(s2);
                                                    newItem.setHistoryDate(util.getDate());
                                                    newItem.setType(typeS);
                                                    // Thêm mục mới vào đầu danh sách data
                                                    data.add(0, newItem);
                                                    historyDAO.insertHistory(history);// thêm lịch sử mới
                                                    userDAO.updateBonusMoney(money1);// thêm tiền vào user luôn
                                                    userDAO.updateTotalSave(money1);// thêm vào tổng tiết kiệm
                                                    yearDAO.updateYmoneyInTotal(money1,String.valueOf(y));// thêm tiền tiêu trong năm mới
                                                    savePersonalChooseColumnMonth(10,money1,Yid);// thêm tiền tiêu trong tháng này
                                                    savePersonalChooseColumnDay(10,money1,Mid,Yid);// thêm tiền tiêu trong ngày hôm nay
                                                    Toast.makeText(view.getContext(),"Thêm giao dịch thành công 🎉",Toast.LENGTH_LONG).show();
                                                    dialog.cancel();
                                                    adapter.notifyDataSetChanged();// load lại data lịch sử  adapter.notifyDataSetChanged();// load lại data lịch sử
                                                }
                                            });
                                            b.create().show();// show dialog
                                        }
                                    }
                                }
                            });
                            dialog.show();
                        }
                    });
                    b.create().show();
                }
            });
            b.setPositiveButton("Tiêu tiền", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.setContentView(R.layout.choose_person_trans_dialog);
                    ImageButton chooseMoving,chooseEat,chooseShop,chooseStudy,chooseRent,chooseLoan,chooseGame,chooseMed,chooseTra,chooseOther;
                    Button cancel;
                    //get ids
                    chooseMoving = dialog.findViewById(R.id.choose_moving);
                    chooseEat = dialog.findViewById(R.id.choose_eat);
                    chooseShop = dialog.findViewById(R.id.choose_shopping);
                    chooseStudy = dialog.findViewById(R.id.choose_study);
                    chooseRent = dialog.findViewById(R.id.choose_Hrent);
                    chooseLoan = dialog.findViewById(R.id.choose_loan);
                    chooseGame = dialog.findViewById(R.id.choose_game);
                    chooseMed = dialog.findViewById(R.id.choose_medical);
                    chooseTra = dialog.findViewById(R.id.choose_travel);
                    chooseOther = dialog.findViewById(R.id.choose_other);
                    cancel = dialog.findViewById(R.id.cancelBut);

                    ImageButton count[] = {chooseMoving,chooseEat,chooseShop,chooseStudy,chooseRent,chooseLoan,chooseGame,chooseMed,chooseTra,chooseOther};
                    checkPersonChoose(count,context);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
            b.create().show();// show dialog

        });
    }
    public void insertNewRows(View view)
    {
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
    }
    // mở dialog thêm tiền
    public void openPersonUseMoneyDialog(String typeS, int i)
    {
        dialog.setContentView(R.layout.set_person_trans_dialog);
        TextView top = dialog.findViewById(R.id.afterChooseTextTop);
        TextInputEditText money = dialog.findViewById(R.id.add_person_mon);
        TextInputEditText note = dialog.findViewById(R.id.add_person_note);
        Button cancel,add;
        cancel = dialog.findViewById(R.id.cancelaBut);
        add = dialog.findViewById(R.id.acceptBut);
        top.setText("Thêm giao dịch "+typeS);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = money.getText().toString().trim();
                String s2 = note.getText().toString().trim();

                // xem nhập vào thỏa mãn không
                if(s2.equals("") || s1.equals(""))
                {
                    Toast.makeText(view.getContext(),"Bạn cần nhập hết các trường thông tin!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    int money1 = Integer.valueOf(s1);
                    if(money1 == 0)
                    {
                        Toast.makeText(view.getContext(),"Số tiền bạn vừa nhập không hợp lý!",Toast.LENGTH_LONG).show();
                    }
                    else // tất cả đều hợp lý
                    {
                        int TotalSave = userDAO.getTotalSave();
                        int After = TotalSave - money1;
                        String saveS = util.formatMoney(After);
                        // su kien alert dialog
                        AlertDialog.Builder b = new AlertDialog.Builder(view.getContext(),R.style.CustomAlertDialogStyle);
                        b.setTitle("Chấp nhận thêm mới?");
                        b.setIcon(R.drawable.faq_icon);
                        if(typeS.equals("Cho vay"))
                        {
                            b.setMessage("Bạn muốn "+"'"+typeS+"' với số tiền "+util.formatMoney(money1)+" VND kèm ghi chú: '"+s2 + "' đúng chứ ?\nNOTE: Lưu ý ghi chú dễ hiểu để sau này dễ xem lại nhé!\nTiền tiết kiệm sẽ còn: "+saveS+" VNĐ");

                        }
                        else
                        {
                            b.setMessage("Bạn muốn thêm giao dịch "+"'"+typeS+"' với số tiền "+util.formatMoney(money1)+" VND kèm ghi chú: '"+s2 + "' đúng chứ ?\nTiền tiết kiệm sẽ còn: "+saveS+" VNĐ");
                        }
                        b.setNegativeButton("Sửa lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int Yid = yearDAO.getYid(String.valueOf(y));
                                int Mid = monthDAO.getMid(String.valueOf(m),Yid);
                                int Did = dayDAO.getDid(String.valueOf(d),Mid,Yid);
                                History history = new History();
                                history.setHistoryName(d+"/"+m+"/"+y);
                                history.setType(typeS);
                                history.setValue(money1);
                                history.setNote(s2);
                                history.setHistoryDate(util.getDate());
                                history.setDid(Did);
                                history.setMid(Mid);
                                history.setYid(Yid);
                                HistoryItem item = new HistoryItem();
                                // Tạo một mục mới
                                HistoryItem newItem = new HistoryItem();
                                newItem.setValue(money1);
                                newItem.setNote(s2);
                                newItem.setHistoryDate(util.getDate());
                                newItem.setType(typeS);
                                // Thêm mục mới vào đầu danh sách data
                                data.add(0, newItem);
                                if(typeS.equals("Cho vay"))
                                {
                                    userDAO.updateYourLoan(money1);// thêm nợ hiện có của người dùng
                                }
                                userDAO.updateTotalSaveDecrease(money1);// giảm tiền tổng đi money1
                                historyDAO.insertHistory(history);// thêm lịch sử mới
                                yearDAO.updateYmoneyOutTotal(money1,String.valueOf(y));// thêm tiền tiêu trong năm mới
                                savePersonalChooseColumnMonth(i,money1,Yid);// thêm tiền tiêu trong tháng này
                                savePersonalChooseColumnDay(i,money1,Mid,Yid);// thêm tiền tiêu trong ngày hôm nay
                                Toast.makeText(view.getContext(),"Thêm giao dịch thành công 🎉",Toast.LENGTH_LONG).show();
                                dialog.cancel();
                                adapter.notifyDataSetChanged();// load lại data lịch sử  adapter.notifyDataSetChanged();// load lại data lịch sử
                            }
                        });
                        b.create().show();// show dialog
                    }
                }

            }
        });
        dialog.show();
    }
    // mở dialog giao dịch cá nhân
    public void openPersonChooseDialog(Dialog dialog)
    {
        dialog.setContentView(R.layout.choose_person_trans_dialog);
    }
    // code này dùng để trả về loại giao dịch mà người dùng chọn
    public void checkPersonChoose(ImageButton imgB[],Context context)
    {
        for(int i=0;i<10;i++)
        {
            int tem =i;
            imgB[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (tem) {
                        case 0:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 1:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 2:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 3:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 4:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 5:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 6:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 7:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 8:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                        case 9:
                            // Xử lý cho trường hợp 1
                            openPersonUseMoneyDialog(returnPersonalChooseText(tem), tem);
                            break;
                    }
                }
            });
        }
    }
    public String returnPersonalChooseText (int i)
    {
        String tem = null;
        switch (i) {
            case 0:
                // Xử lý cho trường hợp 1
                tem ="Di chuyển";
                break;
            case 1:
                // Xử lý cho trường hợp 1
                tem = "Ăn uống";
                break;
            case 2:
                // Xử lý cho trường hợp 1
                tem = "Mua sắm";
                break;
            case 3:
                // Xử lý cho trường hợp 1
                tem = "Học phí";
                break;
            case 4:
                // Xử lý cho trường hợp 1
                tem = "Thuê nhà";
                break;
            case 5:
                // Xử lý cho trường hợp 1
                tem = "Cho vay";
                break;
            case 6:
                // Xử lý cho trường hợp 1
                tem = "Chơi game";
                break;
            case 7:
                // Xử lý cho trường hợp 1
                tem = "Y tế";
                break;
            case 8:
                // Xử lý cho trường hợp 1
                tem = "Du lịch";
                break;
            case 9:
                // Xử lý cho trường hợp 1
                tem = "Khác";
                break;

        }
        return tem;
    }
    public void savePersonalChooseColumnMonth (int i,int money,int Yid)
    {

        switch (i) {
            case 0:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_moving(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 1:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_eat_drink(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 2:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_shopping(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 3:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_study(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 4:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_Hrent(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 5:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_loan(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 6:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_game(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 7:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_medical(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 8:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_bigTravel(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 9:
                // Xử lý cho trường hợp 1
                monthDAO.updateM_otherService(money,String.valueOf(m),Yid);
                monthDAO.updateMmoneyOut(money,String.valueOf(m),Yid);
                break;
            case 10:
                // Xử lý cho trường hợp 1
                monthDAO.updateMmoneyIn(money,String.valueOf(m),Yid);
                break;
        }
    }
    public void savePersonalChooseColumnDay (int i,int money,int Mid,int Yid)
    {

        switch (i) {
            case 0:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_moving(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 1:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_eat_drink(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 2:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_shopping(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 3:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_study(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 4:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_Hrent(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 5:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_loan(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 6:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_game(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 7:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_medical(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 8:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_bigTravel(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 9:
                // Xử lý cho trường hợp 1
                dayDAO.updateD_otherService(money,String.valueOf(d),Mid,Yid);
                dayDAO.updateDmoneyOut(money,String.valueOf(d),Mid,Yid);
                break;
            case 10:
                // Xử lý cho trường hợp 1
                dayDAO.updateDmoneyIn(money,String.valueOf(d),Mid,Yid);
                break;
        }
    }
}