package com.example.loginfirebase;

import com.example.loginfirebase.Farm_Activity.Datos;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Boutique {
    private Datos datos;
    private Medicina Amoxicilina;
    private Medicina Ampicilina;
    private Medicina Aspirina;
    private Medicina Ibuprofeno;
    private Medicina Lansoprazol;
    private Medicina Omeprazol;
    private Medicina Panadol;
    private Medicina Paracetamol;
    private Medicina Promazol;
    private Medicina Ramipril;


    public Medicina getAspirina() {
        return Aspirina;
    }

    public void setAspirina(Medicina aspirina) {
        Aspirina = aspirina;
    }

    public Medicina getIbuprofeno() {
        return Ibuprofeno;
    }

    public void setIbuprofeno(Medicina ibuprofeno) {
        Ibuprofeno = ibuprofeno;
    }

    public Medicina getLansoprazol() {
        return Lansoprazol;
    }

    public void setLansoprazol(Medicina lansoprazol) {
        Lansoprazol = lansoprazol;
    }

    public Medicina getOmeprazol() {
        return Omeprazol;
    }

    public void setOmeprazol(Medicina omeprazol) {
        Omeprazol = omeprazol;
    }

    public Medicina getPanadol() {
        return Panadol;
    }

    public void setPanadol(Medicina panadol) {
        Panadol = panadol;
    }

    public Medicina getParacetamol() {
        return Paracetamol;
    }

    public void setParacetamol(Medicina paracetamol) {
        Paracetamol = paracetamol;
    }

    public Medicina getPromazol() {
        return Promazol;
    }

    public void setPromazol(Medicina promazol) {
        Promazol = promazol;
    }

    public Medicina getRamipril() {
        return Ramipril;
    }

    public void setRamipril(Medicina ramipril) {
        Ramipril = ramipril;
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }
    public Medicina getAmoxicilina() {
        return Amoxicilina;
    }

    public void setAmoxicilina(Medicina amoxicilina) {
        Amoxicilina = amoxicilina;
    }
    public Medicina getAmpicilina() {
        return Ampicilina;
    }

    public void setAmpicilina(Medicina ampicilina) {
        Ampicilina = ampicilina;
    }


    public Boutique(){

    }

    public Boutique(Datos datos, Medicina amoxicilina, Medicina ampicilina, Medicina aspirina, Medicina ibuprofeno, Medicina lansoprazol, Medicina omeprazol, Medicina panadol, Medicina paracetamol, Medicina promazol, Medicina ramipril) {
        this.datos = datos;
        this.Amoxicilina = amoxicilina;
        this.Ampicilina = ampicilina;
        this.Aspirina = aspirina;
        this.Ibuprofeno = ibuprofeno;
        this.Lansoprazol = lansoprazol;
        this.Omeprazol = omeprazol;
        this.Panadol = panadol;
        this.Paracetamol = paracetamol;
        this.Promazol = promazol;
        this.Ramipril = ramipril;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("datos",datos);
        result.put("Amoxicilina",Amoxicilina);
        result.put("Ampicilina" , Ampicilina);
        result.put("Aspirina" , Aspirina);
        result.put("Ibuprofeno" , Ibuprofeno);
        result.put("Lansoprazol" , Lansoprazol);
        result.put("Omeprazol" , Omeprazol);
        result.put("Panadol" , Panadol);
        result.put("Paracetamol" , Paracetamol);
        result.put("Promazol" , Promazol);
        result.put("Ramipril" , Ramipril);

        return result;
    }

}