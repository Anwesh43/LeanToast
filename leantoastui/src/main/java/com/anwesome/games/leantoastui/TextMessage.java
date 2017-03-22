package com.anwesome.games.leantoastui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by anweshmishra on 22/03/17.
 */
public class TextMessage {
    private float y;
    private String text;
    private TextMessage(String text,float y) {
        this.y = y;
        this.text = text;
    }
    public static TextMessage newInstance(String text,float y) {
        return new TextMessage(text,y);
    }
    public void drawMessage(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawText(text,canvas.getWidth()/2-paint.measureText(text)/2,y,paint);
    }
    public int hashCode() {
        return (int)y+text.hashCode();
    }
}
