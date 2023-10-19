package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private TextView tvScore;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvScore = findViewById(R.id.sandulescu_razvan_alexandru_tvScore2);
        btnDone = findViewById(R.id.sandulescu_razvan_alexandru_btnDone);

        //preluam din intent
        String s = getIntent().getStringExtra("SCORE"); //cheia va fi SCORE
        tvScore.setText(s); //in s este tot string-ul cu scor gen 1/8, fix cum revine din activitea Questions, din functia nextQuestion();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pe click, mergem la ecranul principal
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                ScoreActivity.this.startActivity(intent);
                ScoreActivity.this.finish(); //CA SA NU PUTEM DA BACK !!!!!!!!!!!!!!
            }
        });
    }
}