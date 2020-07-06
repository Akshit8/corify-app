package com.ishaanohri.corify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private boolean cb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        SharedPreferences sp = getSharedPreferences("checkbox", 0);
//        cb1 = sp.getBoolean("isLogin", false);

        new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                    //startActivity(new Intent(SplashActivity.this, Maps.class));
                    startActivity(new Intent(SplashActivity.this, UserDetails1.class));
            }
        }.start();

    }
}
