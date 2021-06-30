package com.example.loginfirebase.User_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginfirebase.Boutique;
import com.example.loginfirebase.LoginActivity;
import com.example.loginfirebase.MapsActivity;
import com.example.loginfirebase.MapsActivity2;
import com.example.loginfirebase.R;
import com.example.loginfirebase.ResetPasword;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List_Farmacia extends AppCompatActivity {

   private ListView listviewFa;
    private ArrayList<String> namesUser;
    private Button mostrar;
    private DatabaseReference mDatabase;
    private ArrayList<Boutique> tmpRealTimeBoutiqueUser = new ArrayList<>();
    private ArrayList<Boutique> RealTimeBoutiqueUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__farmacia);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        LeerDatos();
        listviewFa = (ListView) findViewById(R.id.mostrarF);
        mostrar = (Button) findViewById(R.id.xyz);

        namesUser = new ArrayList<String>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, namesUser);
        listviewFa.setAdapter(adapter);


       // for (Boutique a : RealTimeBoutiqueUser) {
          //namesUser.add(RealTimeBoutiqueUser.get(1).getDatos().getCorreo());

         // }


        //imprimir();

       // LeerDatos();

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(List_Farmacia.this, MapsActivity.class));
                namesUser.add("perro");


            }
        });






    }




    /*private void imprimir() {
        //names.clear();
        //names.add("probando");
        Toast.makeText(List_Farmacia.this,"Id:"+RealTimeBoutique.get(1).getNombre(), Toast.LENGTH_SHORT).show();
        for (Boutique a : RealTimeBoutique) {
            names.add(a.getNombre());

        }
    }

*/
    public void LeerDatos(){
        mDatabase.child("Boutique").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boutique np = snapshot.getValue(Boutique.class);
                    tmpRealTimeBoutiqueUser.add(np);
                }
                RealTimeBoutiqueUser.clear();
                RealTimeBoutiqueUser.addAll(tmpRealTimeBoutiqueUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}