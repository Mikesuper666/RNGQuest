package com.redside.rngquest.utils;

import android.os.Handler;
import android.util.Log;

/**
 * Simple clock counts seconds as a chronometer to manage scenes and events more correctly
 * @author Maico Ribeiro (mikersuper666)
 * @since September 19, 2018
 */
public class Clock {
    public static int seconds;
    public static int milis = 0;
    public static Handler handler;
    public static Runnable runnable;
    public static void chronometer(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                milis++;

                if(milis < 1000){
                    seconds++;
                    milis = 0;
                }
            }
        };
        Log.d("chronometer", "Seconds: "+ seconds);
        handler.post(runnable);
    }
}
