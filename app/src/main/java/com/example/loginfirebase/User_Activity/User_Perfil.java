package com.example.loginfirebase.User_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Perfil extends AppCompatActivity {

    //Para mostrar la informacion del Usuaruo lOgeado
    private TextView mTextViewName;
    private TextView mTextViewEmail;
    private ImageView imageViewUsuario;
    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__perfil);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        mAuth =FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //para mostrar en Activy profile
        mTextViewEmail =(TextView) findViewById(R.id.textViewEmail);
        mTextViewName =(TextView) findViewById(R.id.textViewName);
        getUserInfo();
    }

    //Obtener informacion del usuario
    private void getUserInfo(){

        String id=mAuth.getCurrentUser().getUid();

        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String name=dataSnapshot.child("name").getValue().toString();
                    String email=dataSnapshot.child("email").getValue().toString();

                    mTextViewName.setText(name);
                    mTextViewEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}