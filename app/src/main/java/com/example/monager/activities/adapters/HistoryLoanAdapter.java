package com.example.monager.activities.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monager.R;
import com.example.monager.activities.models.History;
import com.example.monager.activities.models.HistoryItem;
import com.example.monager.activities.models.HistoryLoan;
import com.example.monager.activities.sqlite.DayDAO;
import com.example.monager.activities.sqlite.HistoryDAO;
import com.example.monager.activities.sqlite.MonthDAO;
import com.example.monager.activities.sqlite.UserDAO;
import com.example.monager.activities.sqlite.YearDAO;
import com.example.monager.activities.utils;

import java.util.List;

public class HistoryLoanAdapter extends ArrayAdapter <HistoryLoan> {
    private LayoutInflater inflater;
    private int layout;
    private List<HistoryLoan> itemDataList;
    private Context context;
    private TextView totalMon;
    private List<HistoryItem> data;
    public HistoryLoanAdapter(List<HistoryItem> data,TextView textView,Context context, int resource, List<HistoryLoan> itemDataList) {
        super(context, resource, itemDataList);
        this.itemDataList = itemDataList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.totalMon = textView;
        this.data = data;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(this.layout, parent, false);

        TextView date = (TextView) view.findViewById(R.id.datetext);
        TextView value = (TextView) view.findViewById(R.id.valuetext);
        TextView note = (TextView) view.findViewById(R.id.notetext);
        TextView num = (TextView) view.findViewById(R.id.numtext);

        UserDAO userDAO = new UserDAO(context);
        HistoryDAO historyDAO = new HistoryDAO(context);
        YearDAO yearDAO = new YearDAO(context);
        MonthDAO monthDAO = new MonthDAO(context);
        DayDAO dayDAO = new DayDAO(context);
        utils utils = new utils();
        HistoryLoan itemData = getItem(position);
        int money = itemData.getValue();
        date.setText(itemData.getLoanDate());
        utils = new utils();
        value.setText("-"+utils.formatMoney(money)+" VNĐ");
        note.setText(itemData.getLoanNote());
        num.setText(String.valueOf(position+1));

        com.example.monager.activities.utils finalUtils = utils;
        int total = 0;
        String tem = "";
        // change total loan dialog
        for(int i=0; i< itemDataList.size();i++)
        {
            total += itemDataList.get(i).getValue();
        }
        if(total != 0)
        {
            tem = utils.formatMoney(total)+" VNĐ";
            totalMon.setText(tem);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temTotal  = 0;
                // Xử lý sự kiện khi item được click
                // Ví dụ: Lấy vị trí của item được click
                HistoryLoan clickedItem = getItem(position);
                Toast.makeText(context," "+clickedItem.getLoanNote(),Toast.LENGTH_SHORT).show();
                int Hid = clickedItem.getHLoanID();
                        // su kien alert dialog
                AlertDialog.Builder b = new AlertDialog.Builder(context,R.style.CustomAlertDialogStyle);
                b.setTitle("Yêu cầu xóa nợ cũ");
                b.setIcon(R.drawable.faq_icon);
                b.setMessage("Bạn yêu cầu xóa nợ cho giao dịch với thông tin: \nNgày nợ: '"+clickedItem.getLoanDate()+"\nSố tiền: "+clickedItem.getValue()+" VNĐ\nGhi chú: "+clickedItem.getLoanNote());
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int Did = historyDAO.getDidFromHistory(Hid);
                        int Mid = historyDAO.getMidFromHistory(Hid);
                        int Yid = historyDAO.getYidFromHistory(Hid);
                        historyDAO.updateHNote(Hid,"Đã trả");// chuyển note
                        // thêm ItemHistory mới
                        History historyItem = new History();
                        historyItem.setHistoryDate(finalUtils.getDate());
                        historyItem.setType("Thêm tiền");
                        historyItem.setValue(clickedItem.getValue());
                        historyItem.setNote("Xử lý nợ cũ");
                        historyItem.setYid(Yid);
                        historyItem.setMid(Mid);
                        historyItem.setDid(Did);
                        historyItem.setHistoryName("nợ của ngày"+clickedItem.getLoanDate());
                        historyDAO.insertHistory(historyItem);

                        userDAO.updateYourLoanDecrease(clickedItem.getValue());// trừ tiền của tổng nợ
                        yearDAO.updateYmoneyInTotal(clickedItem.getValue(),String.valueOf(finalUtils.getYear()));// thêm tiền năm
                        monthDAO.updateMmoneyIn(clickedItem.getValue(),String.valueOf(finalUtils.getMonth()),Yid);// thêm tiền tháng
                        dayDAO.updateDmoneyIn(clickedItem.getValue(),String.valueOf(finalUtils.getDay()),Mid,Yid);// thêm tiền ngày

                        // Xóa mục được nhấp vào
                        itemDataList.remove(clickedItem);
                        notifyDataSetChanged();
                        // Tạo một mục mới
                        HistoryItem newItem = new HistoryItem();
                        newItem.setValue(clickedItem.getValue());
                        newItem.setNote("Xử lý nợ cũ");
                        newItem.setHistoryDate(finalUtils.getDate());
                        newItem.setType("Thêm tiền");
                        // Thêm mục mới vào đầu danh sách data
                        data.add(0, newItem);
                        // change total loan dialog
                        updateTotalMon();
                    }
                });
                b.create().show();
                // Thực hiện các hành động mong muốn
                // ...

            }
        });

        return view;
    }
    private void updateTotalMon() {
        int total = 0;
        for (HistoryLoan item : itemDataList) {
            total += item.getValue();
        }
        totalMon.setText(utils.formatMoney(total) + " VNĐ");
    }
}
