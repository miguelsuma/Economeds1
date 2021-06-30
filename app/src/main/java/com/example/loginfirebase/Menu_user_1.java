package com.example.loginfirebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Menu_user_1 extends AppCompatActivity {


    private Button acerca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user_1);
        ActionBar ab = getSupportActionBar();
        ab.hide();




    }
}