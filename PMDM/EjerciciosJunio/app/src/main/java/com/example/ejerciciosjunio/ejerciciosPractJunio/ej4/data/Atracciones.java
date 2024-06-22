package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data;

import com.google.gson.annotations.SerializedName;

public class Atracciones {
    @SerializedName("url")
    private String url;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("ocupantes")
    private int ocupantes;

    public String getUrl() {
        return url;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getOcupantes() {
        return ocupantes;
    }
}
