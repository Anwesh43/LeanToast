package com.anwesome.games.leantoastui;

import android.animation.Animator;
import android.animation.ValueAnimator;
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
    private long timeDuration;
    private Activity activity;
    private boolean done = false;
    private LeanToastUIView leanToastUIView;
    public void setText(String text) {
        this.text = text;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public LeanToastUI(Activity activity,String text, long timeDuration) {
        this.text = text;
        this.timeDuration = timeDuration;
        this.activity = activity;
    }
    public void show() {
        if(leanToastUIView == null) {

            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            final int w = dimension.x,h = dimension.y;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    leanToastUIView = new LeanToastUIView(activity);
                    activity.addContentView(leanToastUIView,new ViewGroup.LayoutParams(w,h/10));
                    leanToastUIView.setX(0);
                    leanToastUIView.setY(h);
                    initAnimations(h,h-(3*h)/20);
                }
            });

        }
    }
    public void initAnimations(float startY,float endY) {
        ValueAnimator startAnim = ValueAnimator.ofFloat(startY,endY);
        final ValueAnimator endAnim = ValueAnimator.ofFloat(endY,startY);
        final AnimationAdapter startAnimAdapter = new AnimationAdapter() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                leanToastUIView.setY((float)valueAnimator.getAnimatedValue());
            }
            public void onAnimationEnd(Animator animator) {
                endAnim.start();
            }
        };
        final AnimationAdapter endAnimAdapter = new AnimationAdapter() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                leanToastUIView.setY((float)valueAnimator.getAnimatedValue());
            }
            public void onAnimationEnd(Animator animator) {
                done = true;
            }
        };
        startAnim.setDuration(500);
        endAnim.setDuration(500);
        startAnim.addUpdateListener(startAnimAdapter);
        startAnim.addListener(startAnimAdapter);
        endAnim.addUpdateListener(endAnimAdapter);
        endAnim.addListener(endAnimAdapter);
        endAnim.setStartDelay(timeDuration);
        startAnim.start();
    }
    public boolean isDone() {
        return done;
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
    public int hashCode() {
        return text.hashCode()+(int)timeDuration+leanToastUIView.hashCode();
    }
}
