package com.example.loginfirebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginfirebase.Farm_Activity.Login_Farm;

public class Menu_us_far extends AppCompatActivity {

    private Button mButtonMenuUser;
    private Button mButtonMenuFarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_us_far);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        mButtonMenuUser=(Button)findViewById(R.id.btn_menu1_user);
        mButtonMenuFarm=(Button)findViewById(R.id.btn_menu1_farm);



        mButtonMenuUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_us_far.this,MainActivity.class));
            }
        });


        mButtonMenuFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_us_far.this, Login_Farm.class));
            }
        });
    }
}