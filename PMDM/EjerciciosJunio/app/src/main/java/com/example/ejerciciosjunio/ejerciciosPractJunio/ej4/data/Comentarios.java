package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data;

import com.google.gson.annotations.SerializedName;

public class Comentarios {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("ocupantes")
    private int ocupantes;
    @SerializedName("comentarios")
    private Comentario[] comentarios;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getOcupantes() {
        return ocupantes;
    }

    public Comentario[] getComentarios() {
        return comentarios;
    }
}
