package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data;

import com.google.gson.annotations.SerializedName;
public class Pelicula extends Actor {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("estrellas")
    private String estrellas;
    @SerializedName("actores")
    private Actor[] actores;

    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getEstrellas() {
        return estrellas;
    }
    public Actor[] getActores() {
        return actores;
    }

}
