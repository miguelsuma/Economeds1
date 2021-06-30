package com.example.loginfirebase.Farm_Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginfirebase.Boutique;
import com.example.loginfirebase.MapsActivity;
import com.example.loginfirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.ArrayList;

public class Perfil_Farm extends AppCompatActivity implements Serializable {
    private TextView TextViewNameFarmacia;
    private TextView TextViewTelefono;
    private TextView TextViewDescripcion;
    private ImageView imageViewFarmacia;
    private TextView TextValorizacion;
    private ListView listaMedicamentos;
    private String nombreFarmacia;
    private String descripcionFarmacia;
    private String IDFarmacia;
    private ListView listview;
    private Button btn_buscarfarmacia;
    EditText txt_buscador_farm;

    private ArrayList<String> names;

    public String busqueda = null;
    private DatabaseReference mDatabase;

    private ArrayList<Boutique> tmpRealTimePerfil = new ArrayList<>();
    private ArrayList<Boutique> RealTimePerfil = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__farm);
        mDatabase = FirebaseDatabase.getInstance().getReference();  //INSTANCIA

        LeerDatos();

        ActionBar ab = getSupportActionBar();
        ab.hide();


        Bundle intent = getIntent().getExtras();
        String nombreF = intent.getString("nombre");
        String emailF = intent.getString("email");
        String valorF = intent.getString("valor");
        String telefonoF= intent.getString("telefono");
        String descripcionF= intent.getString("descripcion");
        Boutique farmacia=new Boutique();


        //int val=(Integer) nombreFarmacia;
        //Toast.makeText(Farm_Option_Profile.this,"Id: " + nombreFarmacia, Toast.LENGTH_SHORT).show();

        TextViewNameFarmacia=(TextView) findViewById(R.id.textViewNameFarmaciaP);
        TextViewTelefono=(TextView) findViewById(R.id.textViewTelefonoP);
        TextViewDescripcion=(TextView) findViewById(R.id.textViewDescripcionP);
        TextValorizacion=(TextView) findViewById(R.id.textViewValorizacionP);
        listaMedicamentos=(ListView) findViewById(R.id.listMedicamentosP);

        //TextViewNameFarmacia.setText(email);


                TextViewNameFarmacia.setText(nombreF);
                TextViewTelefono.setText(telefonoF);
                TextViewDescripcion.setText(descripcionF);
                TextValorizacion.setText(valorF);


        //XML MOSTRAR DATOS DE FARMACIA
        //TextViewNameFarmacia.setText(a);
        //TextViewDescripcion.setText(descripcionFarmacia);
        //TextValorizacion.setText(IDFarmacia);



        // Boutique a=new Boutique();
        //a=RealTimeBoutique.get(1);
        //TextViewNameFarmacia.setText(a.getNombre());


        // MostrarDatos(nombreFarmacia);

    }


    public void LeerDatos(){

        mDatabase.child("Boutique").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boutique np = snapshot.getValue(Boutique.class);
                    tmpRealTimePerfil.add(np);
                }
                RealTimePerfil.clear();
                RealTimePerfil.addAll(tmpRealTimePerfil);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}