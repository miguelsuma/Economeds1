package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginfirebase.Boutique;
import com.example.loginfirebase.Farm_Activity.Datos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoutiqueActivity extends AppCompatActivity {

    //atributos de medicamentos en el XMl boutique activity
     EditText amoxicilinaPrecio;
     EditText amoxicilinaPresentacion;
     EditText amoxicilinaCantidad;

     EditText ampicilinaPrecio;
     EditText ampicilinaPresentacion;
     EditText ampicilinaCantidad;

     EditText aspirinaPrecio;
     EditText aspirinaPresentacion;
    EditText aspirinaCantidad;

     EditText ibuprofenoPrecio;
     EditText ibuprofenoPresentacion;
     EditText ibuprofenoCantidad;

     EditText lansoprazolPrecio;
     EditText lansoprazolPresentacion;
     EditText lansoprazolCantidad;

     EditText omeprazolPrecio;
     EditText omeprazolPresentacion;
    EditText omeprazolCantidad;

    EditText panadolPrecio;
    EditText panadolPresentacion;
     EditText panadolCantidad;

    EditText paracetamolPrecio;
     EditText paracetamolPresentacion;
    EditText paracetamolCantidad;

     EditText promazolPrecio;
     EditText promazolPresentacion;
     EditText promazolCantidad;

    EditText rampirilPrecio;
    EditText rampirilPresentacion;
    EditText rampirilCantidad;
    Button btn_Registrar;

    private String amoxicilinaP,amoxicilinaPr,amoxicilinaC;
    private String ampicilinaP,ampicilinaPr,ampicilinaC;
    private String aspirinaP,aspirinaPr,aspirinaC;
    private  String ibuprofenoP,ibuprofenoPr,ibuprofenoC;
    private String lansoprazolP,lansoprazolPr,lansoprazolC;
    private  String omeprazolP,omeprazolPr,omeprazolC;
    private String panadolP,panadolPr,panadolC;
    private String paracetamolP,paracetamolPr,paracetamolC;
    private String promazolP,promazolPr,promazolC;
    private String rampirilP,rampirilPr,rampirilC;

    private  String nombre;
    private  String descripcion;
    private  String password;
    private String valoracion;
    private String correo;
    private  String telefono;

    //datoa de la farmacia
    EditText idF,nomF,desF,passF,valF;
    EditText corrF,telF;
    Boutique b;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    double latitud;
    double longitud;
    int idFarm;


    private DatabaseReference mDatabase;

    private ArrayList<Boutique> tmpRealTimeBoutiqueNF = new ArrayList<>();
    private ArrayList<Boutique> RealTimeBoutiqueNF = new ArrayList<>();

    //objeto Boutique
    //Boutique ;
   // Medicina medicamentos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        LeerDatos();

        Bundle intent = getIntent().getExtras();
        latitud = intent.getDouble("latitud");
        longitud = intent.getDouble("longitud");
        idFarm=intent.getInt("Id");

        //ATRIBUTOS DE LOS EDIT-TEXT
        //atributos de medicamentos en el XMl boutique activity
        amoxicilinaPrecio=(EditText) findViewById(R.id.amoxPrecio);
        amoxicilinaPresentacion=(EditText) findViewById(R.id.amoxPresentacion);
        amoxicilinaCantidad=(EditText) findViewById(R.id.amoxCantidad);

        ampicilinaPrecio=(EditText) findViewById(R.id.ampiPrecio);
        ampicilinaPresentacion=(EditText) findViewById(R.id.amoxPresentacion);
        ampicilinaCantidad=(EditText) findViewById(R.id.ampiCantidad);

        aspirinaPrecio=(EditText) findViewById(R.id.aspiPrecio);
        aspirinaPresentacion=(EditText) findViewById(R.id.aspiPresentacion);
        aspirinaCantidad=(EditText) findViewById(R.id.aspiCantidad);

        ibuprofenoPrecio=(EditText) findViewById(R.id.ibuPrecio);
        ibuprofenoPresentacion=(EditText) findViewById(R.id.ibuPresentacion);
        ibuprofenoCantidad=(EditText) findViewById(R.id.ibuCantidad);

        lansoprazolPrecio=(EditText) findViewById(R.id.lansoPrecio);
        lansoprazolPresentacion=(EditText) findViewById(R.id.lansoPresentacion);
        lansoprazolCantidad=(EditText) findViewById(R.id.lansoCantidad);

        omeprazolPrecio=(EditText) findViewById(R.id.omePrecio);
        omeprazolPresentacion=(EditText) findViewById(R.id.omePresentacion);
        omeprazolCantidad=(EditText) findViewById(R.id.omeCantidad);

        panadolPrecio=(EditText) findViewById(R.id.panaPrecio);
        panadolPresentacion=(EditText) findViewById(R.id.panaPresentacion);
        panadolCantidad=(EditText) findViewById(R.id.panaCantidad);

        paracetamolPrecio=(EditText) findViewById(R.id.paraPrecio);
        paracetamolPresentacion=(EditText) findViewById(R.id.paraPresentacion);
        paracetamolCantidad=(EditText) findViewById(R.id.paraCantidad);

        promazolPrecio=(EditText) findViewById(R.id.promaPrecio);
        promazolPresentacion=(EditText) findViewById(R.id.promaPresentacion);
        promazolCantidad=(EditText) findViewById(R.id.promaCantidad);

        rampirilPrecio=(EditText) findViewById(R.id.rampiPrecio);
        rampirilPresentacion=(EditText) findViewById(R.id.rampiPresentacion);
        rampirilCantidad=(EditText) findViewById(R.id.rampiCantidad);

        //ATRIBUTOS DE LA FARMACIA
        nomF = (EditText) findViewById(R.id.txt_nombreFarmacia);
        desF = (EditText) findViewById(R.id.txt_descripcionFarmacia);
        passF = (EditText) findViewById(R.id.txt_passwordFarmacia);
        valF = (EditText) findViewById(R.id.txt_valoracionFarmacia);
        corrF=(EditText) findViewById(R.id.txt_correo);
        telF=(EditText) findViewById(R.id.txt_telefono);



        //validacionM();
        btn_Registrar= (Button)findViewById(R.id.btn_RegistrarFarmacia);
        //Datos datosF;
       //

        btn_Registrar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //String idval=valF.getText().toString();
                //int valor=Integer.parseInt(idval);

                nombre=nomF.getText().toString();
                descripcion=desF.getText().toString();
                password=passF.getText().toString();
                valoracion=valF.getText().toString();
                correo=corrF.getText().toString();
                telefono=telF.getText().toString();


                amoxicilinaP=amoxicilinaPrecio.getText().toString();
                amoxicilinaPr=amoxicilinaPresentacion.getText().toString();
                amoxicilinaC=amoxicilinaCantidad.getText().toString();


                ampicilinaP=ampicilinaPrecio.getText().toString();
                ampicilinaPr=ampicilinaPresentacion.getText().toString();
                ampicilinaC=ampicilinaCantidad.getText().toString();

                aspirinaP=aspirinaPrecio.getText().toString();
                aspirinaPr=aspirinaPresentacion.getText().toString();
                aspirinaC=aspirinaCantidad.getText().toString();

                ibuprofenoP=ibuprofenoPrecio.getText().toString();
                ibuprofenoPr=ibuprofenoPresentacion.getText().toString();
                ibuprofenoC=ibuprofenoCantidad.getText().toString();

                lansoprazolP=lansoprazolPrecio.getText().toString();
                lansoprazolPr=lansoprazolPresentacion.getText().toString();
                lansoprazolC=lansoprazolCantidad.getText().toString();

                omeprazolP=omeprazolPrecio.getText().toString();
                omeprazolPr=omeprazolPresentacion.getText().toString();
                omeprazolC=omeprazolCantidad.getText().toString();

                panadolP=panadolPrecio.getText().toString();
                panadolPr=panadolPresentacion.getText().toString();
                panadolC=panadolCantidad.getText().toString();

                paracetamolP=paracetamolPrecio.getText().toString();
                paracetamolPr=paracetamolPresentacion.getText().toString();
                paracetamolC=paracetamolCantidad.getText().toString();

                promazolP=promazolPrecio.getText().toString();
                promazolPr=promazolPresentacion.getText().toString();
                promazolC=promazolCantidad.getText().toString();

                rampirilP=rampirilPrecio.getText().toString();
                rampirilPr=rampirilPresentacion.getText().toString();
                rampirilC=rampirilCantidad.getText().toString();


                //validacion();

                if(amoxicilinaP.equals("")){
                    amoxicilinaP="0";
                }
                if(amoxicilinaPr.equals("")){
                    amoxicilinaPr="null";
                }
                if(amoxicilinaC.equals("")){
                    amoxicilinaC="0";
                }


                if(ampicilinaP.equals("")){
                    ampicilinaP="0";
                }
                if(ampicilinaPr.equals("")){
                    ampicilinaPr="NN";
                }
                if(ampicilinaC.equals("")){
                    ampicilinaC="0";
                }

                if(aspirinaP.equals("")){
                    aspirinaP="0";
                }
                if(aspirinaPr.equals("")){
                    aspirinaPr="NN";
                }
                if(aspirinaC.equals("")){
                    aspirinaC="0";
                }

                if(ibuprofenoP.equals("")){
                    ibuprofenoP="0";
                }
                if(ibuprofenoPr.equals("")){
                    ibuprofenoPr="NN";
                }
                if(ibuprofenoC.equals("")){
                    ibuprofenoC="0";
                }

                if(lansoprazolP.equals("")){
                    lansoprazolP="0";
                }
                if(lansoprazolPr.equals("")){
                    lansoprazolPr="NN";
                }
                if(lansoprazolC.equals("")){
                    lansoprazolC="0";
                }

                if(omeprazolP.equals("")){
                    omeprazolP="0";
                }
                if(omeprazolPr.equals("")){
                    omeprazolPr="NN";
                }
                if(omeprazolC.equals("")){
                    omeprazolC="0";
                }

                if(panadolP.equals("")){
                    panadolP="0";
                }
                if(panadolPr.equals("")){
                    panadolPr="NN";
                }
                if(panadolC.equals("")){
                    panadolC="0";
                }

                if(paracetamolP.equals("")){
                    paracetamolP="0";
                }
                if(paracetamolPr.equals("")){
                    paracetamolPr="NN";
                }
                if(paracetamolC.equals("")){
                    paracetamolC="0";
                }

                if(promazolP.equals("")){
                    promazolP="0";
                }
                if(promazolPr.equals("")){
                    promazolPr="NN";
                }
                if(promazolC.equals("")){
                    promazolC="0";
                }

                if(rampirilP.equals("")){
                    rampirilP="0";
                }
                if(rampirilPr.equals("")){
                    rampirilPr="NN";
                }
                if(rampirilC.equals("")){
                    rampirilC="0";
                }


                // if(nomF.equals("")||desF.equals("")||passF.equals("")||valF.equals("")||corrF.equals("")||telF.equals("")){
                    //validacion();
                //}
                //else{

                    //int valor=Integer.parseInt();
                    int val = Integer.parseInt(valoracion);

                String idFarma=idFarm+"";
                    Datos datosF=new Datos(idFarma,nombre,descripcion,password,latitud,longitud,val,telefono,correo);

                    int c1=Integer.parseInt(amoxicilinaC);
                    double p1=Double.parseDouble(amoxicilinaP);
                    Medicina amoxi=new Medicina(amoxicilinaPr,p1,"Amoxicilina",c1);

                    int c2=Integer.parseInt(ampicilinaC);
                    double p2=Double.parseDouble(ampicilinaP);
                    Medicina ampi=new Medicina(ampicilinaPr,p2,"Ampicilina",c2);

                    int c3=Integer.parseInt(aspirinaC);
                    double p3=Double.parseDouble(aspirinaP);
                    Medicina aspi=new Medicina(aspirinaPr,p3,"Aspirina",c3);

                    int c4=Integer.parseInt(ibuprofenoC);
                    double p4=Double.parseDouble(ibuprofenoP);
                    Medicina ibu=new Medicina(ibuprofenoPr,p4,"Ibuprofeno",c4);

                    int c5=Integer.parseInt(lansoprazolC);
                    double p5=Double.parseDouble(lansoprazolP);
                    Medicina lanso=new Medicina(lansoprazolPr,p5,"Lansoprazol",c5);

                    int c6=Integer.parseInt(omeprazolC);
                    double p6=Double.parseDouble(omeprazolP);
                    Medicina ome=new Medicina(omeprazolPr,p6,"Omeprazol",c6);

                    int c7=Integer.parseInt(panadolC);
                    double p7=Double.parseDouble(panadolP);
                    Medicina pana=new Medicina(panadolPr,p7,"Panadol",c7);

                    int c8=Integer.parseInt(paracetamolC);
                    double p8=Double.parseDouble(paracetamolP);
                    Medicina para=new Medicina(paracetamolPr,p8,"Paracetamol",c8);

                    int c9=Integer.parseInt(promazolC);
                    double p9=Double.parseDouble(promazolP);
                    Medicina proma=new Medicina(promazolPr,p9,"Promazol",c9);

                    int c10=Integer.parseInt(rampirilC);
                    double p10=Double.parseDouble(rampirilP);
                    Medicina rampi=new Medicina(rampirilPr,p10,"Rampiril",c10);

                    //public Medicina(String presentacion, int precio , String nombre,int cantidad)

                   Boutique botica =new Boutique(datosF, amoxi, ampi, aspi,ibu,lanso, ome, pana, para, proma, rampi);

                    mDatabase.child("Boutique").child(idFarma).setValue(botica);

                startActivity(new Intent(BoutiqueActivity.this,MapsActivity2.class));


               // }

            }
        });


    }



    public void LeerDatos() {

        mDatabase.child("Boutique").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boutique np = snapshot.getValue(Boutique.class);
                    tmpRealTimeBoutiqueNF.add(np);
                }
                RealTimeBoutiqueNF.clear();
                RealTimeBoutiqueNF.addAll(tmpRealTimeBoutiqueNF);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
/*
    private void validacion() {

        if (nombre.equals("")){
            nomF.setError("Required");
        }
        else if(descripcion.equals("")){
            desF.setError("Required");
        }else if(password.equals("")) {
            passF.setError("Required");
        }else if(valoracion.equals("")){
            valF.setError("Required");
        }else if(correo.equals("")){
            corrF.setError("Required");
        }else if(telefono.equals("")){
            telF.setError("Required");

        }
    }
    */
    }