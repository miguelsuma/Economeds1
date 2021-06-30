package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasword extends AppCompatActivity {

    private EditText mEditTextEmail;
    private Button mButtonResetPassword;

    //variable golban campo texto del correo
    private String email="";

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pasword);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        mAuth =FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);   //contexto q es esta actividad

        mEditTextEmail=(EditText) findViewById(R.id.editTextEmail);
        mButtonResetPassword =(Button) findViewById(R.id.btnResetPassword);

        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email =mEditTextEmail.getText().toString();

                if(!email.isEmpty()){
                    mDialog.setMessage("Espere un Momento... ");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPasswordUser();
                }

                else{
                    Toast.makeText(ResetPasword.this,"Debe Ingresar el Email",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void resetPasswordUser(){

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                        Toast.makeText(ResetPasword.this, "Se envio el correo para restablcer contraseña",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(ResetPasword.this, "No se pudo enviar el correo de restablcer contraseña",Toast.LENGTH_SHORT).show();
                }

                mDialog.dismiss();  //Que se oculte el mDialog
            }
        });


    }
}