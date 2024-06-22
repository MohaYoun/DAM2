package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data;

import com.google.gson.annotations.SerializedName;

public class Actor {
    @SerializedName("pelicula")
    private String pelicula;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("url")
    private String url;

    public String getPelicula() {
        return pelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }
}
