package com.example.monager.activities;

import android.os.Handler;

public class utils {
    // Delay mechanism

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
}
