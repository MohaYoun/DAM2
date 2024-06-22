package com.example.ejerciciosjunio.ejerciciosPractJunio.ejArmamento.data;

import java.util.ArrayList;

public class Armamento {
    private String nombre;
    private String pais;
    private String tipo;

    public Armamento(String nombre, String pais, String tipo) {
        this.nombre = nombre;
        this.pais = pais;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getTipo() {
        return tipo;
    }

    public static ArrayList<Armamento> generaArmamento(){
        ArrayList<Armamento> armas = new ArrayList<>();
        armas.add(new Armamento("Leopard", "Alemania", "x"));
        armas.add(new Armamento("RG-31","Sudafrica", "x"));
        armas.add(new Armamento("Shahed", "Iran", "o"));
        armas.add(new Armamento("MQ-9", "EEUU", "o"));
        armas.add(new Armamento("Tomahawk", "EEUU","i"));
        armas.add(new Armamento("ASRAAM", "UK","i"));
        return armas;
    }
}
