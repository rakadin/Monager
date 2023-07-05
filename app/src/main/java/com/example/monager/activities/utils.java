package com.example.monager.activities;

import android.os.Handler;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class utils {
    // Delay mechanism
    public int year;
    public interface DelayCallback{
        void afterDelay();
    }

    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 100); // afterDelay will be executed after (secs*1000) milliseconds.
    }

    // get day, month, year
    Calendar calendar = Calendar.getInstance();
    public int getYear()
    {
        int year = calendar.get(Calendar.YEAR);
        return year;
    }
    public int getMonth()
    {
        int month = calendar.get(Calendar.MONTH)+1;
        return month;
    }
    public int getDay()
    {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }
    // format num 1000 to 1,000 func
    public String formatMoney(int number)
    {
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        nf.setGroupingUsed(true);
        String formattedNumber = nf.format(number);
        return formattedNumber;
    }

}
