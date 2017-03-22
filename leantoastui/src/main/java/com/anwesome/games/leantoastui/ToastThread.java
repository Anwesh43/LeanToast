package com.anwesome.games.leantoastui;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class ToastThread {
    private ConcurrentLinkedQueue<LeanToastUI> leanToastUIs = new ConcurrentLinkedQueue<>();
    public boolean running = false;
    private Thread toastThread;
    private ToastThreadRunner toastThreadRunner = new ToastThreadRunner();
    private boolean started = false;
    public void addLeanToastUi(LeanToastUI leanToastUI) {
        leanToastUIs.add(leanToastUI);
        if(!running) {
            if(started) {
                stopThread();
            }
            toastThread = new Thread(toastThreadRunner);
            running = true;
            started = true;
            toastThread.start();
        }
    }
    public void stopThread() {
        Log.d("status","stopping thread");
        while(true) {
            try {
                toastThread.join();
                break;
            }
            catch (Exception ex) {
                Log.e("exception",ex.toString());
            }
        }
        started = false;
        Log.d("status","thread is stopped");
    }
    public LeanToastUI getFirtLeanToast() {
        for(LeanToastUI leanToastUI:leanToastUIs) {
            return leanToastUI;
        }
        return null;
    }
    private class ToastThreadRunner implements Runnable {
        public void run() {
            while(running) {
                LeanToastUI leanToastUI = getFirtLeanToast();
                if(leanToastUI!=null) {
                    leanToastUI.show();
                }
                else {
                    running = false;
                    break;
                }
                if(leanToastUI.isDone()) {
                    leanToastUIs.remove(leanToastUI);
                }
            }
        }
    }

}
