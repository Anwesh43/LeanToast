package com.anwesome.games.leantoastui;

import android.app.Activity;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class LeanToast {
    public static final long LENGTH_LONG = 8000l;
    public static final long LENGTH_SHORT = 3000l;
    private static ToastThread toastThread = new ToastThread();
    public static void show(Activity activity,String text,long timeDuration) {
        LeanToastUI leanToastUI = new LeanToastUI(activity,text,timeDuration);
        toastThread.addLeanToastUi(leanToastUI);
    }
}
