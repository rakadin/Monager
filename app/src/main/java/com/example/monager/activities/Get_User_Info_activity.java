package com.example.monager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monager.R;
import com.example.monager.activities.models.User;
import com.example.monager.activities.sqlite.UserDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class Get_User_Info_activity extends AppCompatActivity {
    private TextInputEditText surname,name,Salary,SalTime,TotalSave,Spend;
    private TextView getsur,getname,getsal,getsaltime,gettotal,getspend;
    private Button nextbut;
    protected int count =0;
    private User user;
    private UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_get_user_info);
        //getIDs
        getIDs();
        user = new User();
        userDAO = new UserDAO(Get_User_Info_activity.this);

        nextbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thêm chức năng kiểm soát nhập ở từng ô từng giai đoạn vào ví dụ như dấu 1,000 và ngày trong tháng thì phải > 0 và < 31
                // cho trim các đầu chữ tên họ, đảm bảo là phải lưu dữ liệu và người dùng đồng ý mới được count++ và tiếp tục
                verifyInput(view.getContext());//run verify

            }
        });
    }
    public void getIDs()
    {
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        Salary = findViewById(R.id.salary);
        SalTime = findViewById(R.id.SalTime);
        TotalSave = findViewById(R.id.TotalSave);
        Spend = findViewById(R.id.Spend);

        getsur = findViewById(R.id.textView2);
        getname = findViewById(R.id.textView3);
        getsal = findViewById(R.id.txtSalary);
        getsaltime = findViewById(R.id.txtSalTime);
        gettotal = findViewById(R.id.txtTotalSave);
        getspend = findViewById(R.id.txtSpend);

        nextbut = findViewById(R.id.nextBut);
    }
    // verify input texts func
    public void verifyInput(Context context)
    {
        if(count == 0)// verify name and surname
        {
            String surString = surname.getText().toString().trim();
            String nameString = name.getText().toString().trim();
            if(surString.equals("") || nameString.equals(""))
            {
                Toast.makeText(context,"Xin hãy điền đủ thông tin!",Toast.LENGTH_LONG).show();
            }
            if(surString.equals("") == false && nameString.equals("") == false)
            {
                // su kien alert dialog
                AlertDialog.Builder b = new AlertDialog.Builder(context,R.style.CustomAlertDialogStyle);
                b.setTitle("Lấy thông tin người dùng");
                b.setIcon(R.drawable.faq_icon);
                b.setMessage("Xin chào "+surString+" "+nameString+" 👏 rất vui được đồng hành cùng bạn🤩");
                b.setPositiveButton("Tiếp theo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.setUserName(nameString);
                        user.setUserSurname(surString);
                        count++;
                        controlButFunc();
                    }
                });
                b.create().show();// show dialog
            }
        }
        if(count == 1)// verify salary and sal time
        {
            if(Salary.getText().toString().trim().equals("") || SalTime.getText().toString().trim().equals(""))
            {
                Toast.makeText(context,"Xin hãy điền đủ thông tin!",Toast.LENGTH_LONG).show();
            }
            if(Salary.getText().toString().trim().equals("") == false && SalTime.getText().toString().trim().equals("") == false)
            {
                int salaryInt = Integer.parseInt(Salary.getText().toString());
                int salTimeInt = Integer.parseInt(SalTime.getText().toString());
                if(salTimeInt > 31 || salTimeInt<1)
                {
                    Toast.makeText(context,"Ngày trong tháng không hợp lệ!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedSal = decimalFormat.format(salaryInt);
                    // su kien alert dialog
                    AlertDialog.Builder b = new AlertDialog.Builder(context,R.style.CustomAlertDialogStyle);
                    b.setTitle("Chấp nhận thêm thông tin");
                    b.setIcon(R.drawable.faq_icon);
                    b.setMessage("Vậy mức lương/trợ cấp của bạn là: "+formattedSal+" VND và bạn nhận nó vào ngày "+salTimeInt+" hàng tháng đúng chứ ?");
                    b.setPositiveButton("Đúng rồi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            user.setSalary(salaryInt);
                            user.setSalTime(salTimeInt);
                            count++;
                            controlButFunc();
                        }
                    });
                    b.setNegativeButton("Sửa lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    b.create().show();// show dialog
                }
            }
        }
        if(count == 2) // verify TotalSave and spending level
        {
            String totalString = TotalSave.getText().toString().trim();
            String spendString = Spend.getText().toString().trim();
            if(totalString.equals("")||spendString.equals(""))
            {
                Toast.makeText(context,"Ngày trong tháng không hợp lệ!",Toast.LENGTH_LONG).show();
            }
            if(totalString.equals("") == false && spendString.equals("")== false)
            {
                int totalInt = Integer.parseInt(totalString);
                int spendInt = Integer.parseInt(spendString);
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String formattedTotal = decimalFormat.format(totalInt);
                String formattedSpend = decimalFormat.format(spendInt);
                // su kien alert dialog
                AlertDialog.Builder b = new AlertDialog.Builder(context,R.style.CustomAlertDialogStyle);
                b.setTitle("Chấp nhận thêm thông tin");
                b.setIcon(R.drawable.faq_icon);
                b.setMessage("Vậy hiện tại bạn đã tích được: "+formattedTotal+" VNĐ và bạn muốn đặt hạn mức chi tiêu cho một tháng là "+formattedSpend+" VNĐ đúng chứ ?");
                b.setPositiveButton("Đúng rồi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.setTotalSave(totalInt);
                        user.setMSpendingLevel(spendInt);
                        count++;
                        controlButFunc();
                    }
                });
                b.setNegativeButton("Sửa lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                b.create().show();// show dialog
            }
        }
    }
    public void controlButFunc()
    {
        if(count==1)
        {
            surname.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            getsur.setVisibility(View.GONE);
            getname.setVisibility(View.GONE);
            Salary.setVisibility(View.VISIBLE);
            SalTime.setVisibility(View.VISIBLE);
            getsal.setVisibility(View.VISIBLE);
            getsaltime.setVisibility(View.VISIBLE);
        }
        if(count==2)
        {
            Salary.setVisibility(View.GONE);
            SalTime.setVisibility(View.GONE);
            getsal.setVisibility(View.GONE);
            getsaltime.setVisibility(View.GONE);
            TotalSave.setVisibility(View.VISIBLE);
            Spend.setVisibility(View.VISIBLE);
            gettotal.setVisibility(View.VISIBLE);
            getspend.setVisibility(View.VISIBLE);
        }
        if(count == 3)
        {
            sendData(user);
            // gửi thông tin lên là k còn là người dùng mới
            SharedPreferences locationpref3 = getApplicationContext()
                    .getSharedPreferences("MainActivity", MODE_PRIVATE);
            SharedPreferences.Editor spedit = locationpref3.edit();
            spedit.putInt("firsttime", 5);
            spedit.apply();
            Intent intent = new Intent();
            intent.setClass(this, HomePage_activity.class);
            startActivity(intent);
        }
    }
    public void sendData(User user)
    {
        user.setUserPass("");
        user.setBonusMoney(0);
        user.setYourLoan(0);
        userDAO.insertUser(user);
    }
}