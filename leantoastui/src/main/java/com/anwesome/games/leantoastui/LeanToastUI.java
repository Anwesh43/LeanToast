package com.anwesome.games.leantoastui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class LeanToastUI{
    private int color = Color.parseColor("#1976D2");
    private String text;
    private long timeDuration;
    private Activity activity;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean done = false;
    private LeanToastUIView leanToastUIView;
    private List<TextMessage> messages = new ArrayList<>();
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
                    paint.setTextSize(h/40);
                    leanToastUIView = new LeanToastUIView(activity);
                    float x = paint.getTextSize()/2,y = paint.getTextSize();
                    String tokens[] = text.split(" ");
                    String msg="";
                    for(String token:tokens) {
                        if(paint.measureText(msg+token)>w-h/40) {
                            if(messages.size()==0) {
                                y+=paint.getTextSize()/2;
                            }
                            messages.add(TextMessage.newInstance(msg,y));
                            msg = token;
                            y+=(paint.getTextSize()*2);
                        }
                        else {
                            msg = msg+token;
                        }
                    }
                    messages.add(TextMessage.newInstance(msg,y+paint.getTextSize()));
                    y+=3*paint.getTextSize();
                    activity.addContentView(leanToastUIView,new ViewGroup.LayoutParams(w,(int)y));
                    leanToastUIView.setX(0);
                    leanToastUIView.setY(h);
                    y = Math.max(h/5,y);
                    initAnimations(h,h-(h)/10-y);
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
        public LeanToastUIView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.parseColor("#00000000"));
            paint.setColor(color);
            int w = canvas.getWidth(),h = canvas.getHeight(),triH = h/5;
            canvas.drawRoundRect(new RectF(w/10,triH,w-w/10,h-triH),w/10,w/10,paint);
            Path path = new Path();
            path.moveTo(w/5,triH);
            path.lineTo(w/5+triH,triH);
            path.lineTo(w/5+triH/2,0);
            canvas.drawPath(path,paint);
            for(TextMessage textMessage:messages) {
                textMessage.drawMessage(canvas,paint);
            }
        }
    }
    public int hashCode() {
        return text.hashCode()+(int)timeDuration+leanToastUIView.hashCode();
    }
}
