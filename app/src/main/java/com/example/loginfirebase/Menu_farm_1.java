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

import com.example.loginfirebase.Farm_Activity.Login_Farm;
import com.example.loginfirebase.Farm_Activity.Perfil_Farm;
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

public class Menu_farm_1 extends AppCompatActivity {

    private Button mButtonSignOutF;
    //para mostar en le Profile


    private Button mButttonInfo;
    private Button mButtonMapsF;
    private Button mButtonListFarm;
    private Button mButtonMyFarm;
private Button acerca;
   // private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_farm_1);
       Bundle intent = getIntent().getExtras();
        String nombre = intent.getString("nombre");
        String email = intent.getString("email");
        String descripcion = intent.getString("descripcion");
        String valor = intent.getString("valor");
        String telefono = intent.getString("telefono");

        ActionBar ab = getSupportActionBar();
        ab.hide();

        //BTN_MAPA
        mButttonInfo=findViewById(R.id.btn_InfoFarmacia);
        mButtonMapsF=findViewById(R.id.btn_MapsFarmacia);
        mButtonListFarm=findViewById(R.id.btn_farmacia_listFarmacia);
        mButtonMyFarm=findViewById(R.id.btn_miFarmacia);


        /*boton farmacia

        Button farmacia=findViewById(R.id.btn_id);
        farmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,FarmaciaActivity.class));

            }
        });

*/

        acerca=(Button)findViewById(R.id.btn_AcercaDe1);

        acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Menu_farm_1.this,AcercaDeActivity.class));
            }
        });



        //BOTONES PARA LOS DIFERENTES MENUS QUE SE MOSTRARAN
        mButttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_farm_1.this,Perfil_Farm.class);
                //Toast.makeText(buscador.this,"Id: " + names.get(i), Toast.LENGTH_SHORT).show();
                //intent.putExtra("email", email);
                startActivity(intent);

            }
        });

        //MAPA
        mButtonMapsF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_farm_1.this, MapsActivity2.class));

            }
        });

        mButtonListFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_farm_1.this, List_Farmacia.class));

            }
        });


        mButtonMyFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu_farm_1.this,Perfil_Farm.class);
                //Toast.makeText(buscador.this,"Id: " + names.get(i), Toast.LENGTH_SHORT).show();
                intent.putExtra("nombre", nombre);
                intent.putExtra("email", email);
                intent.putExtra("descripcion", descripcion);
                intent.putExtra("valor", valor);
                intent.putExtra("telefono", telefono);
                startActivity(intent);




                //startActivity(new Intent(Menu_farm_1.this, Perfil_Farm.class));

            }
        });



        //  setContentView(R.layout.activity_main);//todo el contenido se visualiza en activity main

        //mAuth =FirebaseAuth.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference();

       // mButtonSignOut =(Button) findViewById(R.id.btnSignout);

        //para mostrar en Activy profile
        //mTextViewEmail =(TextView) findViewById(R.id.textViewEmail);
        //mTextViewName =(TextView) findViewById(R.id.textViewName);


        /*
        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                //Se cierra la sesion y se regresa al login o registro
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
            }
        });

        */
        // getUserInfo();

    }


    //@Override
   /* public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
*/
    //BOTONES
    //MAPA




}