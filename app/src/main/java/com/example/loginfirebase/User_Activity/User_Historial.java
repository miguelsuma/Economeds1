package com.example.loginfirebase.User_Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loginfirebase.R;

public class User_Historial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__historial);
        ActionBar ab = getSupportActionBar();
        ab.hide();
    }
}