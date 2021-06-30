package com.example.loginfirebase.Farm_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginfirebase.Boutique;
import com.example.loginfirebase.Menu_farm_1;
import com.example.loginfirebase.ProfileActivity;
import com.example.loginfirebase.R;
import com.example.loginfirebase.ResetPasword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Login_Farm extends AppCompatActivity {

    private EditText mEditTextEmail;       //email studenID
    private EditText mEditTextPassword;     //contraseña PIN

    private Button mButtonLogin;
    private Button mButtonPrevioRegistro; //para registrar farmacia

    //para restablcer contraseña
    private Button mButtonResetPassword;

    //elementos para iniciar sesion
    private String email= "";
    private String password = "";

    //registro de usuario
    //private FirebaseAuth mAuth;
    private DatabaseReference ref;


    ////##############################################33
    private ListView listview;
    private Button btn_buscarfarmacia;
    EditText txt_buscador_farm;

    private ArrayList<String> names;

    public String busqueda = null;
    private DatabaseReference mDatabase;
    private ArrayList<Boutique> tmpRealTimeBoutiqueF = new ArrayList<>();
    private ArrayList<Boutique> RealTimeBoutiqueF = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__farm);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        //instanacimos mAuht
        // mAuth =FirebaseAuth.getInstance();

        mEditTextEmail=(EditText) findViewById(R.id.editTextEmailF);
        mEditTextPassword=(EditText) findViewById(R.id.editTextPasswordF);
        mButtonLogin=(Button) findViewById(R.id.btnLoginF);
        mButtonResetPassword=(Button)findViewById(R.id.btnSendToResetPasswordF);

        mButtonPrevioRegistro=(Button)findViewById(R.id.btn_registrar_farmacia);

        //para el login
        //ref= FirebaseDatabase.getInstance().getReference().child("Users");


        mDatabase = FirebaseDatabase.getInstance().getReference();
        LeerDatos();



        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=mEditTextEmail.getText().toString();
                password =mEditTextPassword.getText().toString();

                if(!email.isEmpty()&&!password.isEmpty()){

                    for (Boutique a : RealTimeBoutiqueF) {
                        if(email.equals(a.getDatos().getCorreo())&&password.equals(a.getDatos().getPassword())){

                            Intent intent = new Intent(Login_Farm.this,Menu_farm_1.class);
                            //Toast.makeText(buscador.this,"Id: " + names.get(i), Toast.LENGTH_SHORT).show();
                            intent.putExtra("nombre", a.getDatos().getNombre());
                            intent.putExtra("email", a.getDatos().getCorreo());
                            intent.putExtra("descripcion", a.getDatos().getDescripcion());
                            intent.putExtra("valor", a.getDatos().getValoracion());
                            intent.putExtra("telefono", a.getDatos().getTelefono());

                            startActivity(intent);

                            //startActivity(new Intent(Login_Farm.this, Menu_farm_1.class));
                        }

                        else{
                            Toast.makeText(Login_Farm.this, "DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //loginUser();

                }

                else{
                    Toast.makeText(Login_Farm.this, "commplete los campos vacios", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Farm.this, ResetPasword.class));

            }
        });


        //este e trabajo

        mButtonPrevioRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Farm.this,Farm_Previo_Registro.class));

            }
        });


    }

   /* private void loginUser(){

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login_Farm.this, Menu_farm_1.class));
                    finish();
                }
                else{
                    Toast.makeText(Login_Farm.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }*/

    //EN EST COPIE

    public void LeerDatos(){

        mDatabase.child("Boutique").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boutique np = snapshot.getValue(Boutique.class);
                    tmpRealTimeBoutiqueF.add(np);
                }
                RealTimeBoutiqueF.clear();
                RealTimeBoutiqueF.addAll(tmpRealTimeBoutiqueF);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}