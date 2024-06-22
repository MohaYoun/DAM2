package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data;

import com.google.gson.annotations.SerializedName;

public class Comentario {
    @SerializedName("texto")
    private String texto;

    public String getTexto() {
        return texto;
    }
}
