 package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

 public class LoginActivity extends AppCompatActivity {

     private EditText mEditTextEmail;
     private EditText mEditTextPassword;
     private Button mButtonLogin;

    //para restablcer contraseña
     private Button mButtonResetPassword;

     //elementos para iniciar sesion
     private String email= "";
     private String password = "";

     //registro de usuario
     private FirebaseAuth mAuth;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         ActionBar ab = getSupportActionBar();
         ab.hide();
        //instanacimos mAuht
         mAuth =FirebaseAuth.getInstance();

         mEditTextEmail=(EditText) findViewById(R.id.editTextEmail);

         mEditTextPassword=(EditText) findViewById(R.id.editTextPassword);
         mButtonLogin=(Button) findViewById(R.id.btnLogin);
         mButtonResetPassword=(Button)findViewById(R.id.btnSendToResetPassword);


         mButtonLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 email=mEditTextEmail.getText().toString();
                 password =mEditTextPassword.getText().toString();

                 if(!email.isEmpty()&&!password.isEmpty()){

                     loginUser();

                 }

                 else{
                     Toast.makeText(LoginActivity.this, "commplete los campos vacios", Toast.LENGTH_SHORT).show();
                 }

             }
         });

         mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(LoginActivity.this,ResetPasword.class));

             }
         });
    }

    private void loginUser(){

         mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                     finish();
                 }
                 else{
                     Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }
}