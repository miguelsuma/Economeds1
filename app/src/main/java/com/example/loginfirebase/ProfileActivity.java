package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginfirebase.User_Activity.List_Farmacia;
import com.example.loginfirebase.User_Activity.User_Buscador;
import com.example.loginfirebase.User_Activity.User_Historial;
import com.example.loginfirebase.User_Activity.User_Perfil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private Button mButtonSignOut;
    //para mostar en le Profile
    private TextView mTextViewName;
    private TextView mTextViewEmail;
    private DatabaseReference mDatabase;
    private Button mButtonMapas;
    private Button mButtonBuscador;
    private Button mButtonListfarmacia;
    private Button mButtonPerfilUsuario;
    private Button mButtonHistorial;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        //BTN_MAPA
        mButtonMapas=findViewById(R.id.btn_Maps);
        mButtonBuscador=findViewById(R.id.btn_Buscador);
        mButtonListfarmacia=findViewById(R.id.btn_List_farmacia);
        mButtonPerfilUsuario=findViewById(R.id.btn_usuarioPerfil);
        mButtonHistorial=findViewById(R.id.btn_historial);

        /*boton farmacia

        Button farmacia=findViewById(R.id.btn_id);
        farmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,FarmaciaActivity.class));

            }
        });

*/



        //BOTONES PARA LOS DIFERENTES MENUS QUE SE MOSTRARAN
        mButtonMapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,MapsActivity.class));

            }
        });

        mButtonHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, User_Historial.class));

            }
        });

        mButtonPerfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, User_Perfil.class));

            }
        });


        mButtonBuscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, User_Buscador.class));

            }
        });

        //BOTON LISTA FARMACIAS
        mButtonListfarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, List_Farmacia.class));

            }
        });

        //  setContentView(R.layout.activity_main);//todo el contenido se visualiza en activity main

        mAuth =FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mButtonSignOut =(Button) findViewById(R.id.btnSignout);

        //para mostrar en Activy profile
        mTextViewEmail =(TextView) findViewById(R.id.textViewEmail);
        mTextViewName =(TextView) findViewById(R.id.textViewName);

        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                //Se cierra la sesion y se regresa al login o registro
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
            }
        });

       // getUserInfo();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //BOTONES
    //MAPA




}