package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.example.loginfirebase.Farm_Activity.Farm_Option_Profile;
import com.example.loginfirebase.Farm_Activity.Perfil_Farm;
import com.example.loginfirebase.model.Farmacia;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    //VARIALBES
    private GoogleMap mMap;
    private Marker marker1;

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    private FusedLocationProviderClient fusedLocationClient;

    private double myLatitud;
    private double myLongitud;
    private String fid;
    //public String nombre;
    //private String descripcion;
    private String password;
    //private double Latitud;
    //private double Longitud;
    private int valoracion;
    private DatabaseReference mDatabase;
    private ArrayList<Marker> tmpRealTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> RealTimeMarkers = new ArrayList<>();

    //vARIABLES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //myPosicion();
        //countDownTimer();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LeerPuntos();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Map<String, Object> latlang = new HashMap<>();
                            myLatitud = location.getLatitude();
                            myLongitud = location.getLongitude();
                            LatLng myPosition = new LatLng(myLatitud, myLongitud);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 13));
                        }
                    }
                });

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);

    }


    //NUEVO LEER PUNTOS

    private void LeerPuntos() {
        mDatabase.child("Boutique").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (Marker marker : RealTimeMarkers) {
                    marker.remove();
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boutique np = snapshot.getValue(Boutique.class);
                    Double Latitud = np.getDatos().getLatitud();
                    Double Longitud = np.getDatos().getLongitud();
                    String nombre = np.getDatos().getNombre();
                    String id = np.getDatos().getFid();
                    String descripcion = np.getDatos().getDescripcion();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(Latitud, Longitud)).title(nombre).snippet(descripcion);
                    tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));
                }
                RealTimeMarkers.clear();
                RealTimeMarkers.addAll(tmpRealTimeMarkers);
                //countDownTimer();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w("loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    //LEER PUNTOS


    @Override
    public void onMapLongClick(LatLng latLng) {
        // agregarFarmacia a =new agregarFarmacia(latLng);
        Intent intent = new Intent(this, BoutiqueActivity.class);
        //Toast.makeText(MapsActivity2.this,"Id: " + latLng.latitude, Toast.LENGTH_SHORT).show();
        intent.putExtra("latitud", latLng.latitude);
        intent.putExtra("longitud", latLng.longitude);
        intent.putExtra("Id",RealTimeMarkers.size()+1);
        startActivity(intent);
        /*mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .anchor(0.0f,1.0f)
                .position(latLng));*/

    }


    //





}