package com.example.loginfirebase.Farm_Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loginfirebase.Boutique;
import com.example.loginfirebase.R;
import com.example.loginfirebase.Comentarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Farm_Option_Profile extends AppCompatActivity {
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

    private double lati1;
    private double long1;
    private String posicion;

    private Button btn_comentarios;

    private ArrayList<String> names;

    public String busqueda = null;
    private DatabaseReference mDatabase;

    private ArrayList<Boutique> tmpRealTimeBoutique = new ArrayList<>();
    private ArrayList<Boutique> RealTimeBoutique = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm__option__profile);
        mDatabase = FirebaseDatabase.getInstance().getReference();  //INSTANCIA

        LeerDatos();
        ActionBar ab = getSupportActionBar();
        ab.hide();

        Bundle intent = getIntent().getExtras();
        nombreFarmacia = intent.getString("nombre");
        descripcionFarmacia = intent.getString("descripcion");
        IDFarmacia = intent.getString("valoracion");
        lati1 =intent.getDouble("latitud1");
        long1 =intent.getDouble("longitud1");







        //int val=(Integer) nombreFarmacia;
        //Toast.makeText(Farm_Option_Profile.this,"Id: " + nombreFarmacia, Toast.LENGTH_SHORT).show();

        TextViewNameFarmacia=(TextView) findViewById(R.id.textViewNameFarmacia);
        TextViewTelefono=(TextView) findViewById(R.id.textViewTelefono);
        TextViewDescripcion=(TextView) findViewById(R.id.textViewDescripcion);
        TextValorizacion=(TextView) findViewById(R.id.textViewValorizacion);
        listaMedicamentos=(ListView) findViewById(R.id.listMedicamentos);

        //btn comentarios
        btn_comentarios=(Button) findViewById(R.id.comentarios);

        btn_comentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Farm_Option_Profile.this, comentarios.class));


                Intent intent = new Intent(Farm_Option_Profile.this, Comentarios.class);  //redirecciona al perfil de usuario

                intent.putExtra("valoracion",IDFarmacia);
                //intent.putExtra("descripcion",marker.getSnippet());
                //intent.putExtra("valoracion",marker.getId());
                //intent.putExtra("b", entero);
                //intent.putExtra("a", marker.getPosition().longitude);
                startActivity(intent);



            }
        });

        posicion=lati1+","+long1;

        //XML MOSTRAR DATOS DE FARMACIA
        TextViewNameFarmacia.setText(nombreFarmacia);
        TextViewDescripcion.setText(descripcionFarmacia);
        TextValorizacion.setText(IDFarmacia);
        TextViewTelefono.setText(posicion);

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
                    tmpRealTimeBoutique.add(np);
                }
                RealTimeBoutique.clear();
                RealTimeBoutique.addAll(tmpRealTimeBoutique);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}