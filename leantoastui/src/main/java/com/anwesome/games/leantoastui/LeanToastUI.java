package com.anwesome.games.leantoastui;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class LeanToastUI{
    private int color = Color.parseColor("#1976D2");
    private String text;
    private int timeDuration;
    private Activity activity;
    private LeanToastUIView leanToastUIView;
    public void setText(String text) {
        this.text = text;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public LeanToastUI(Activity activity,String text, int timeDuration) {
        this.text = text;
        this.timeDuration = timeDuration;
        this.activity = activity;
    }
    public void show() {
        if(leanToastUIView == null) {
            leanToastUIView = new LeanToastUIView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x,h = dimension.y;
            activity.addContentView(leanToastUIView,new ViewGroup.LayoutParams(w,h/10));
            leanToastUIView.setX(0);
            leanToastUIView.setY((9*h)/10-h/20);
        }
    }
    private class LeanToastUIView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public LeanToastUIView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.parseColor("#00000000"));
            paint.setColor(color);
            int w = canvas.getWidth(),h = canvas.getHeight(),triH = h/5;
            canvas.drawRoundRect(new RectF(w/10,h/5,w-w/10,h-h/5),w/10,w/10,paint);
            Path path = new Path();
            path.lineTo(w/5,h/5);
            path.lineTo(w/5+h/5,h/5);
            path.lineTo(w/5+h/10,0);
            canvas.drawPath(path,paint);
        }
    }
}
