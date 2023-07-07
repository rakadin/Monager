package com.example.monager.activities.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monager.R;
import com.example.monager.activities.models.HistoryItem;
import com.example.monager.activities.utils;

import java.util.List;

public class HistoryItemAdapter extends ArrayAdapter<HistoryItem> {
    private LayoutInflater inflater;
    private int layout;
    public utils utils;
    private List<HistoryItem> itemDataList;
    public HistoryItemAdapter(Context context, int resource, List<HistoryItem> itemDataList) {
        super(context, resource, itemDataList);
        this.itemDataList = itemDataList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(this.layout, parent, false);

       TextView type = (TextView) view.findViewById(R.id.typetext);
       TextView date = (TextView) view.findViewById(R.id.datetext);
       TextView value = (TextView) view.findViewById(R.id.valuetext);
       TextView note = (TextView) view.findViewById(R.id.notetext);
       ImageView logo = (ImageView) view.findViewById(R.id.logo);
        String temS = "";
        HistoryItem itemData = getItem(position);
        type.setText(itemData.getType());
        date.setText(itemData.getHistoryDate());
        int temMoney = itemData.getValue();
        if(itemData.getType().equals("Thêm tiền"))
        {
            temS ="+";
        }
        else
        {
            temS ="-";
        }
        utils = new utils();
        value.setText(temS+utils.formatMoney(temMoney)+" VNĐ");
        note.setText(itemData.getNote());
        if(itemData.getNote().length() > 18)
        {
            note.setSelected(true);
        }
        logo.setImageResource(returnItemLogoID(itemData.getType()));

        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
            View child = ((ViewGroup) view).getChildAt(i);
            child.setFocusable(false);
            child.setFocusableInTouchMode(false);
        }
        return view;
    }
    // return logo
    public int returnItemLogoID (String type)
    {
        int tem = 0;
        switch (type) {
            case "Di chuyển":
                // Xử lý cho trường hợp 1
                tem = R.drawable.taxi_icon;
                break;
            case "Ăn uống":
                // Xử lý cho trường hợp 1
                tem = R.drawable.eat_icon;
                break;
            case "Mua sắm":
                // Xử lý cho trường hợp 1
                tem = R.drawable.shopping_icon;
                break;
            case "Học phí":
                // Xử lý cho trường hợp 1
                tem = R.drawable.study_icon;
                break;
            case "Thuê nhà":
                // Xử lý cho trường hợp 1
                tem = R.drawable.rent_icon;
                break;
            case "Cho vay":
                // Xử lý cho trường hợp 1
                tem = R.drawable.money_loan_icon;
                break;
            case "Chơi game":
                // Xử lý cho trường hợp 1
                tem = R.drawable.game_icon;
                break;
            case "Y tế":
                // Xử lý cho trường hợp 1
                tem = R.drawable.medical_icon;
                break;
            case "Du lịch":
                // Xử lý cho trường hợp 1
                tem = R.drawable.travel_icon;
                break;
            case "Khác":
                // Xử lý cho trường hợp 1
                tem = R.drawable.other_icon;
                break;
            case "Thêm tiền":
                // Xử lý cho trường hợp 1
                tem = R.drawable.bonus_icon;
                break;
        }
        return tem;
    }
}
