package com.anwesome.games.leantoastui;

import android.app.Activity;
import android.graphics.Color;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class LeanToast {
    public static final long LENGTH_LONG = 6000l;
    public static final long LENGTH_SHORT = 2000l;
    private static int color =  Color.parseColor("#03A9F4");
    private static ToastThread toastThread = new ToastThread();
    public static void show(Activity activity,String text,long timeDuration) {
        LeanToastUI leanToastUI = new LeanToastUI(activity,text,timeDuration);
        leanToastUI.setColor(color);
        toastThread.addLeanToastUi(leanToastUI);
    }
    public static void setColor(int newColor) {
        color = newColor;
    }
}
