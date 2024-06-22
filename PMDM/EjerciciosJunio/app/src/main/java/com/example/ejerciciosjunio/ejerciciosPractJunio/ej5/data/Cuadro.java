package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data;

import com.google.gson.annotations.SerializedName;

public class Cuadro {
    @SerializedName("url")
    private String url;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("precio")
    private double precio;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("tecnica")
    private String tecnica;
    @SerializedName("color")
    private String color;
    public String getUrl() {
        return url;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public String getFecha() {
        return fecha;
    }
    public String getTecnica() {
        return tecnica;
    }
    public String getColor() {
        return color;
    }
}