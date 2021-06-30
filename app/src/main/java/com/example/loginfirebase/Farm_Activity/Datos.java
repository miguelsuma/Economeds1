package com.example.loginfirebase.Farm_Activity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Datos {
    private String fid;
    private String nombre;
    private String descripcion;
    private String password;
    private double Latitud;
    private double Longitud;
    private int valoracion;
    private String telefono;
    private String correo;

    public Datos(){

    }
    public Datos(String fid, String nombre, String descripcion, String password, double Latitud, double Longitud, int valoracion,String telefono, String correo){
        this.fid = fid;
        this.nombre = nombre;
        this.descripcion=descripcion;
        this.password=password;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
        this.valoracion=valoracion;
        this.correo=correo;
        this.telefono=telefono;

    }
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("fid",fid);
        result.put("nombre",nombre);
        result.put("descripcion" , descripcion);
        result.put("Latitud",Latitud);
        result.put("Longitud",Longitud);
        result.put("valoracion",valoracion);
        result.put("telefono",telefono);
        result.put("correo",correo);

        return result;
    }
}
