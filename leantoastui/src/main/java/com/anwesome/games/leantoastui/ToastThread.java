package com.anwesome.games.leantoastui;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class ToastThread {
    private ConcurrentLinkedQueue<LeanToastUI> leanToastUIs = new ConcurrentLinkedQueue<>();
    public boolean running = false;
    private int called = 0;
    private Thread toastThread;
    public void addLeanToastUi(LeanToastUI leanToastUI) {
        leanToastUIs.add(leanToastUI);
        if(called == 0) {
            toastThread = new Thread(new ToastThreadRunner());
        }
        if(!running) {
            running = true;
            toastThread.start();
        }
        called++;
    }
    public void stopThread() {
        while(true) {
            try {
                toastThread.join();
                break;
            }
            catch (Exception ex) {

            }
        }
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
            if(!running) {
                stopThread();
            }
        }
    }

}
