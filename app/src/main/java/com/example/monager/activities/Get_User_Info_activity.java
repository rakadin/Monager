package com.example.monager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.monager.R;
import com.google.android.material.textfield.TextInputEditText;

public class Get_User_Info_activity extends AppCompatActivity {
    private TextInputEditText surname,name,Salary,SalTime,TotalSave,Spend;
    private TextView getsur,getname,getsal,getsaltime,gettotal,getspend;
    private Button nextbut;
    protected int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_get_user_info);
        //getIDs
        getIDs();
        nextbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thêm chức năng kiểm soát nhập ở từng ô từng giai đoạn vào ví dụ như dấu 1,000 và ngày trong tháng thì phải > 0 và < 31
                // cho trim các đầu chữ tên họ, đảm bảo là phải lưu dữ liệu và người dùng đồng ý mới được count++ và tiếp tục
                count++;
                controlButFunc();
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
            Intent intent = new Intent();
            intent.setClass(this, HomePage_activity.class);
            startActivity(intent);
        }
    }
}