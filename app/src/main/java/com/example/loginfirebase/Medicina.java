package com.example.loginfirebase;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Medicina {
    private String presentacion;
    private double precio;
    private String nombre;

    private int cantidad;
    public Medicina(){

    }

    public Medicina(String presentacion, double precio , String nombre,int cantidad){
        this.precio = precio;
        this.presentacion = presentacion;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("precio",precio);
        result.put("presentacion",presentacion);
        result.put("nombre" , nombre);
        result.put("cantidad",cantidad);
        return result;
    }

}