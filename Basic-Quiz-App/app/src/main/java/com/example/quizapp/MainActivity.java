package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvQA;
    private Button btnSQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQA = findViewById(R.id.sandulescu_razvan_alexandru_tvQA);
        btnSQ = findViewById(R.id.sandulescu_razvan_alexandru_btnSQ);

        //la fel ca la SplashActivity; setam font-ul
        Typeface typeface = ResourcesCompat.getFont(this, R.font.pacifico);
        tvQA.setTypeface(typeface);

        //facem click-ul functional
        //cand se apasa butonul, se va porni activitatea "Questions"
        btnSQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Questions.class);
                startActivity(intent);
            }
        });
    }
}