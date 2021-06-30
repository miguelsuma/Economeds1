package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginfirebase.model.Farmacia;
import com.example.loginfirebase.model.Medicamento;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FarmaciaActivity extends AppCompatActivity {

    private List<Farmacia> listFarmacia =new ArrayList<Farmacia>();  // 1 DE DICIEMBRE //LISTA DE FARMCIAS FUNCION NO SE SABE

    ArrayAdapter<Farmacia> arrayAdapterFarmacia;             //  ARRAY FARMCIA CON SUS DATOS

    EditText idF,nomF,depF,provF,distF,dirF,telF,horF;//aqui tambien añadi -----//EDITSELECTION
    ListView listv_farmacia;

    //estos dos datos nos permiten trabajar con la bd de firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Farmacia f;

    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;   //CHEKOBCK MEDAICMANETOS

    private List<Medicamento> listaMedicamentos=new ArrayList<Medicamento>(); //LIST DE MEDICAMENTOS DE LOS CHECKBOK

    Farmacia farmaciaSeleccionada;              //PARA ACTUALIZAR DATOS

    public void añadirMedicamentos(){
        Medicamento m1=new Medicamento();
        m1.setMid("Ibuprofeno");
        m1.setNombre("Ibuprofeno");
        m1.setContenido("400 mg");
        m1.setPresentacion("Tableta");
        m1.setPrecio(1.00);
        Medicamento m2=new Medicamento();
        m2.setMid("Paracetamol");
        m2.setNombre("Paracetamol");
        m2.setContenido("100 mg");
        m2.setPresentacion("Solucion");
        m2.setPrecio(1.00);
        Medicamento m3=new Medicamento();
        m3.setMid("Amoxicilina");
        m3.setNombre("Amoxicilina");
        m3.setContenido("250 mg");
        m3.setPresentacion("Suspencion");
        m3.setPrecio(1.00);
        Medicamento m4=new Medicamento();
        m4.setMid("Zomiprerin");
        m4.setNombre("Zomiprerin");
        m4.setContenido("50 mg");
        m4.setPresentacion("Inyectable");
        m4.setPrecio(1.00);
        Medicamento m5=new Medicamento();
        m5.setMid("Nootropil");
        m5.setNombre("Nootropil");
        m5.setContenido("1200 mg");
        m5.setPresentacion("Tableta");
        m5.setPrecio(1.00);
        Medicamento m6=new Medicamento();
        m6.setMid("Nasaclor");
        m6.setNombre("Nasaclor");
        m6.setContenido("5 ml");
        m6.setPresentacion("Jarabe");
        m6.setPrecio(1.00);
        Medicamento m7=new Medicamento();
        m7.setMid("Nafolix");
        m7.setNombre("Nafolix");
        m7.setContenido("2,5 mg");
        m7.setPresentacion("Jarabe");
        m7.setPrecio(1.00);
        Medicamento m8=new Medicamento();
        m8.setMid("Melopral");
        m8.setNombre("Melopral");
        m8.setContenido("40 mg");
        m8.setPresentacion("Tableta");
        m8.setPrecio(1.00);
        Medicamento m9=new Medicamento();
        m9.setMid("Ventolin");
        m9.setNombre("Ventolin");
        m9.setContenido("Dosis");
        m9.setPresentacion("Aerosol");
        m9.setPrecio(1.00);
        Medicamento m10=new Medicamento();
        m10.setMid("Frenadol");
        m10.setNombre("Frenadol");
        m10.setContenido("1 g");
        m10.setPresentacion("Tableta");
        m10.setPrecio(1.00);

        listaMedicamentos.add(m1);
        listaMedicamentos.add(m2);
        listaMedicamentos.add(m3);
        listaMedicamentos.add(m4);
        listaMedicamentos.add(m5);
        listaMedicamentos.add(m6);
        listaMedicamentos.add(m7);
        listaMedicamentos.add(m8);
        listaMedicamentos.add(m9);
        listaMedicamentos.add(m10);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia);
        //ActionBar ab = getSupportActionBar();
        //ab.hide();
        Bundle intent = getIntent().getExtras();
        double a = intent.getDouble("b");     //latitud
        double b = intent.getDouble("a");     //Longitud

       // super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_farmacia);//todo el contenido se visualiza en activity main

        idF=findViewById(R.id.txt_idFarmacia);          // capturando los datos
        nomF=findViewById(R.id.txt_nombreFarmacia);
        depF=findViewById(R.id.txt_departamentoFarmacia);
        provF=findViewById(R.id.txt_provinciaFarmacia);
        distF=findViewById(R.id.txt_distritoFarmacia);
        dirF=findViewById(R.id.txt_direccionFarmacia);
        telF=findViewById(R.id.txt_telefonoFarmacia);
        horF=findViewById(R.id.txt_horarioFarmacia);

        listv_farmacia=findViewById(R.id.lv_datos);  //lv datos

        inicializarFirebase();
        listarDatos();//1 DE DICIEMBRE
        añadirMedicamentos();
        c1=findViewById(R.id.cb_medicamento1);
        c1.setText(listaMedicamentos.get(0).getNombre().toString());
        c2=findViewById(R.id.cb_medicamento2);
        c2.setText(listaMedicamentos.get(1).getNombre().toString());
        c3=findViewById(R.id.cb_medicamento3);
        c3.setText(listaMedicamentos.get(2).getNombre().toString());
        c4=findViewById(R.id.cb_medicamento4);
        c4.setText(listaMedicamentos.get(3).getNombre().toString());
        c5=findViewById(R.id.cb_medicamento5);
        c5.setText(listaMedicamentos.get(4).getNombre().toString());
        c6=findViewById(R.id.cb_medicamento6);
        c6.setText(listaMedicamentos.get(5).getNombre().toString());
        c7=findViewById(R.id.cb_medicamento7);
        c7.setText(listaMedicamentos.get(6).getNombre().toString());
        c8=findViewById(R.id.cb_medicamento8);
        c8.setText(listaMedicamentos.get(7).getNombre().toString());
        c9=findViewById(R.id.cb_medicamento9);
        c9.setText(listaMedicamentos.get(8).getNombre().toString());
        c10=findViewById(R.id.cb_medicamento10);
        c10.setText(listaMedicamentos.get(9).getNombre().toString());


        //PARA MODIFICAR
        listv_farmacia.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //LLENA LOS RECUADROS UNA VEZ Q SE HIZO CLICK EN ALGUNA FARMACIA
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                farmaciaSeleccionada=(Farmacia)parent.getItemAtPosition(position);
                idF.setText(farmaciaSeleccionada.getFid());//opteniendo datos
                nomF.setText(farmaciaSeleccionada.getNombre());
                depF.setText(farmaciaSeleccionada.getDepartamento());
                provF.setText(farmaciaSeleccionada.getProvincia());
                distF.setText(farmaciaSeleccionada.getDistrito());
                dirF.setText(farmaciaSeleccionada.getDireccion());
                telF.setText(farmaciaSeleccionada.getTelefono());
                horF.setText(farmaciaSeleccionada.getHorario());

            }
        });
    }

    private void listarDatos() {

        databaseReference.child("pato").addValueEventListener(new ValueEventListener() {
            @Override



            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listFarmacia.clear();

                for(DataSnapshot objSnaptshot :snapshot.getChildren()){
                    Farmacia fa=objSnaptshot.getValue(Farmacia.class);
                    listFarmacia.add(fa);

                    arrayAdapterFarmacia=new ArrayAdapter<Farmacia>(FarmaciaActivity.this, android.R.layout.simple_list_item_1,listFarmacia);//adopta lista de farmacia
                    listv_farmacia.setAdapter(arrayAdapterFarmacia);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }





    private void inicializarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase=firebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override

    //SELECCIONAR CHECKBOX

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String id=idF.getText().toString();    //esto añadi-----
        String nombre=nomF.getText().toString();
        String departamento=depF.getText().toString();
        String provincia=provF.getText().toString();
        String distrito=distF.getText().toString();
        String direccion=dirF.getText().toString();
        String telefono=telF.getText().toString();
        String horario=horF.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:
                if(id.equals("")||nombre.equals("")||departamento.equals("")||provincia.equals("")||distrito.equals("")||direccion.equals("")||telefono.equals("")||horario.equals("")){
                    validacion();
                }else {
                    f=new Farmacia();
                    // m.setMid(UUID.randomUUID().toString());
                    f.setFid(id);//esto añadi--------------------------------
                    f.setNombre(nombre);
                    f.setDepartamento(departamento);
                    f.setProvincia(provincia);
                    f.setDistrito(distrito);
                    f.setDireccion(direccion);
                    f.setTelefono(telefono);
                    f.setHorario(horario);
                    databaseReference.child("pato").child(f.getFid()).setValue(f);//AGREGA DATOS A COLLECCION
                    //String z=f.getFid();
                    //databaseReference.child("Farmacia/"+f.getFid()+"/Medicamentos").child(f.getFid()).setValue(f);
                    if(c1.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(0).getMid()).setValue(listaMedicamentos.get(0));
                    }
                    if(c2.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(1).getMid()).setValue(listaMedicamentos.get(1));
                    }
                    if(c3.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(2).getMid()).setValue(listaMedicamentos.get(2));
                    }
                    if(c4.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(3).getMid()).setValue(listaMedicamentos.get(3));
                    }
                    if(c5.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(4).getMid()).setValue(listaMedicamentos.get(4));
                    }
                    if(c6.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(5).getMid()).setValue(listaMedicamentos.get(5));
                    }
                    if(c7.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(6).getMid()).setValue(listaMedicamentos.get(6));
                    }
                    if(c8.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(7).getMid()).setValue(listaMedicamentos.get(7));
                    }
                    if(c9.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(8).getMid()).setValue(listaMedicamentos.get(8));
                    }
                    if(c10.isChecked()==true){
                        databaseReference.child("pato/"+f.getFid()+"/Medicamentos").child(listaMedicamentos.get(9).getMid()).setValue(listaMedicamentos.get(9));
                    }

                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
                break;

            case R.id.icon_save:  //BOTON PARA GUARDAR
                Farmacia f=new Farmacia();
                f.setFid(farmaciaSeleccionada.getFid());
                f.setNombre(nomF.getText().toString().trim());
                f.setDepartamento(depF.getText().toString().trim());
                f.setProvincia(provF.getText().toString().trim());
                f.setDistrito(distF.getText().toString().trim());
                f.setDireccion(dirF.getText().toString().trim());
                f.setTelefono(telF.getText().toString().trim());
                f.setHorario(horF.getText().toString().trim());
                databaseReference.child("pato").child(f.getFid()).setValue(f);
                limpiarCajas();
                Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show();

                break;
            case R.id.icon_delete:  //BOTON DE BORRAR
                Farmacia f_eliminar=new Farmacia();
                f_eliminar.setFid(farmaciaSeleccionada.getFid());
                databaseReference.child("pato").child(f_eliminar.getFid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            default:break;
        }
        return true;
    }

    //LIMPIA LAS CAJAS
    private void limpiarCajas() {

        idF.setText("");
        nomF.setText("");
        depF.setText("");
        provF.setText("");
        distF.setText("");
        dirF.setText("");
        telF.setText("");
        horF.setText("");
    }

    private void validacion() {
        String id=idF.getText().toString();
        String nombre=nomF.getText().toString();
        String departamento=depF.getText().toString();
        String provincia=provF.getText().toString();
        String distrito=distF.getText().toString();
        String direccion=dirF.getText().toString();
        String telefono=telF.getText().toString();
        String horario=horF.getText().toString();

        if (id.equals("")){
            idF.setError("Required");
        }
        else if(nombre.equals("")){
            nomF.setError("Required");
        }else if(departamento.equals("")) {
            depF.setError("Required");
        }else if(provincia.equals("")){
            provF.setError("Required");
        }else if(distrito.equals("")){
            distF.setError("Required");
        }else if(direccion.equals("")){
            dirF.setError("Required");
        }else if(telefono.equals("")){
            telF.setError("Required");
        }else if(horario.equals("")){
            horF.setError("Required");
        }
    }
}