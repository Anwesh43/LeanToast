package com.anwesome.ui.leantoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LeanToast extends AppCompatActivity {
    private int time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_toast);
        Button showToast = (Button)findViewById(R.id.showtoast);
        Button showShorterToast = (Button)findViewById(R.id.show_short_toast);
        final TextView numberOfToasts = (TextView)findViewById(R.id.number_of_texts);
        showToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time++;
                com.anwesome.games.leantoastui.LeanToast.show(LeanToast.this,"More More More More More More More Showing LeanToast"+(time), com.anwesome.games.leantoastui.LeanToast.LENGTH_LONG);
                numberOfToasts.setText("number of toasts "+time);
            }
        });
        showShorterToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time++;
                com.anwesome.games.leantoastui.LeanToast.show(LeanToast.this,"More More More More More More More Showing LeanToast"+(time), com.anwesome.games.leantoastui.LeanToast.LENGTH_SHORT);
                numberOfToasts.setText("number of toasts "+time);
            }
        });
    }
}
