package com.muhameddhouibi.designthinking.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.muhameddhouibi.designthinking.R;

public class HomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home_intent = new Intent(HomeActivity.this,IntroActivity.class);
                startActivity(home_intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
