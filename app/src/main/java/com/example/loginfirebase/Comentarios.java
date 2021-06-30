package com.example.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Comentarios extends AppCompatActivity {
    EditText editComentario;
    Button btn_comentar;
    private ListView listview;
    private ArrayList<String> names;
    public String comentario =null;
    private DatabaseReference mDatabase;
    private ArrayList<Boutique> tmpRealTimeComentario = new ArrayList<>();
    private ArrayList<Boutique> RealTimeComentario = new ArrayList<>();

    private int numeroHijos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //LeerDatos2();

        Bundle intent = getIntent().getExtras();
        String id = intent.getString("valoracion");

        String valId = id.substring(1);
        int index = Integer.parseInt(valId);


        //botones

        listview = (ListView) findViewById(R.id.list_view2);
        editComentario = (EditText) findViewById(R.id.editComentario);
        btn_comentar = (Button) findViewById(R.id.btn_comentar);

        names = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);
        leerComentarios(valId);

        //LlenarComent(id);
        btn_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mensaje = editComentario.getText().toString();
                //long numHijos2 = snapshot.child("comentario").getChildrenCount();
                int nume = (int) numeroHijos + 1;
                String nume2 = nume + "";
                mDatabase.child("Boutique").child(valId).child("comentario").child(nume2).setValue(mensaje);
                leerComentarios(valId);

            }
        });
    }

        public void leerComentarios(String valId){
        mDatabase.child("Boutique").child(valId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if(dataSnapshot.exists()){

                        long numHijos = dataSnapshot.child("comentario").getChildrenCount();
                        numeroHijos=(int)numHijos;
                        for (int i=1;i<=numeroHijos;i++){
                            String indice=i+"";
                            String comentario=dataSnapshot.child("comentario").child(indice).getValue().toString();

                            names.add(comentario);

                        }



                        //int guion = comentario.indexOf("=");
                        //System.out.println(guion);
                        //String cadena = comentario.substring(guion+1, comentario.length()-1);
                        //names.add(comentario);
                      //String email=dataSnapshot.child("email").getValue().toString();

                        //mTextViewName.setText(name);
                        //mTextViewEmail.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }

    public void LeerDatos2(){
        mDatabase.child("Boutique").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boutique np = snapshot.getValue(Boutique.class);
                    tmpRealTimeComentario.add(np);
                }
                RealTimeComentario.clear();
                RealTimeComentario.addAll(tmpRealTimeComentario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}