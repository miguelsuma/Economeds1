package com.example.loginfirebase.User_Activity;

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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.loginfirebase.Boutique;
import com.example.loginfirebase.MapsActivity;
import com.example.loginfirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class User_Buscador extends AppCompatActivity {


    private ListView   listview;
    private Button btn_buscarfarmacia;
    EditText txt_buscador_farm;

    private ArrayList<String> names;

    public String busqueda = null;
    private DatabaseReference mDatabase;
    private ArrayList<Boutique> tmpRealTimeBoutique = new ArrayList<>();
    private ArrayList<Boutique> RealTimeBoutique = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__buscador);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        LeerDatos();

        listview = (ListView) findViewById(R.id.listview);          //LISTA DE BUSQUEDA
        btn_buscarfarmacia = (Button) findViewById(R.id.btn_buscar_farmacia); //BOTON BUSCAR
        txt_buscador_farm= (EditText) findViewById(R.id.txt_buscador);    //del xml edit tex buscador


        names = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);


        ActionBar ab = getSupportActionBar();
        ab.hide();


        btn_buscarfarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                names.clear();

                busqueda = txt_buscador_farm.getText().toString();
                switch (busqueda){

                    case "Amoxicilina":

                        for (Boutique a : RealTimeBoutique) {
                            if(a.getAmoxicilina().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getAmoxicilina().getPrecio());

                            }
                        }
                        break;
                    case "Ampicilina":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getAmpicilina().getCantidad() >1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getAmpicilina().getPrecio());
                            }
                        }
                        break;
                    case "Aspirina":
                        //names.add("falla");
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getAspirina().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getAspirina().getPrecio());


                            }
                        }
                        break;
                    case "Ibuprofeno":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getIbuprofeno().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getIbuprofeno().getPrecio());
                            }
                        }
                        break;
                    case "Lansoprazol":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getLansoprazol().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getLansoprazol().getPrecio());
                            }
                        }
                        break;
                    case "Omeprazol":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getOmeprazol().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getOmeprazol().getPrecio());
                            }
                        }
                        break;
                    case "Panadol":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getPanadol().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getPanadol().getPrecio());
                            }
                        }
                        break;
                    case "Paracetamol":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getParacetamol().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getParacetamol().getPrecio());
                            }
                        }
                        break;
                    case "Promazol":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getPromazol().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getPromazol().getPrecio());
                            }
                        }
                        break;
                    case "Ramipril":
                        for (Boutique a : RealTimeBoutique) {
                            if(a.getRamipril().getCantidad()>1){
                                names.add(a.getDatos().getNombre()+" \nPrecio "+a.getRamipril().getPrecio());
                            }
                        }
                        break;
                    default: break;
                }

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(User_Buscador.this, MapsActivity.class);
                //Toast.makeText(buscador.this,"Id: " + names.get(i), Toast.LENGTH_SHORT).show();
                //intent.putExtra("nombre", names.get(i));
                startActivity(intent);
            }
        });



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