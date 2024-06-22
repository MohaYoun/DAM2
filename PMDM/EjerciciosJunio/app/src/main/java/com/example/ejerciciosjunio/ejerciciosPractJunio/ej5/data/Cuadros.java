package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data;

import com.google.gson.annotations.SerializedName;

public class Cuadros {
    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("precio")
    private double precio;

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
}