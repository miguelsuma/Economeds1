package com.example.loginfirebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        lanzarThread();
    }
    private void lanzarThread(){
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000); //espera de 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this, Menu_us_far.class);
                    startActivity(intent);
                }
            }
        };

        timer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}