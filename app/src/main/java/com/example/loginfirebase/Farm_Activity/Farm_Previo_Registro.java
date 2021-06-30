package com.example.loginfirebase.Farm_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginfirebase.MapsActivity2;
import com.example.loginfirebase.Menu_farm_1;
import com.example.loginfirebase.R;

public class Farm_Previo_Registro extends AppCompatActivity {

    private Button mButtonMapsF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm__previo__registro);

        mButtonMapsF=findViewById(R.id.Mapa_registro_Farmacia);


        mButtonMapsF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Farm_Previo_Registro.this, MapsActivity2.class));

            }
        });

    }
}