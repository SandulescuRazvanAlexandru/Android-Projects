package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {
    private TextView tvCSQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //initializez variabila (altfel este null pointer)
        tvCSQ = findViewById(R.id.sandulescu_razvan_alexandru_tvCSQ);

        //vreau sa atasez fontul meu variabilei de tip textview
        //cream o variabila de tip font, pe care apoi o asociem textview-ului
        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        tvCSQ.setTypeface(typeface);

        //facem animatie pentru textview
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.myanim);
        tvCSQ.setAnimation(animation);

        //acum fac un Thread. dar de ce?
        //ei bine, vreau ca dupa animatia de 3 secunde sa porneasca activititatea principala
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000); //dupa cele 3 secunde, se va deschide activitatea principala
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //pornesc activitatea main
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }).start();
    }
}