package com.anwesome.games.leantoastui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class DimensionsUtil {
    public static Point getDeviceDimension(Activity activity) {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Point size = new Point();
        Display display = displayManager.getDisplay(0);
        display.getRealSize(size);
        return size;
    }
}
