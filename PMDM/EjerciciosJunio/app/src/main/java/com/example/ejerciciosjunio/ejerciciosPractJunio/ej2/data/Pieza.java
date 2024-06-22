package com.example.ejerciciosjunio.ejerciciosPractJunio.ej2.data;

import java.util.ArrayList;

public class Pieza {
    private  String nombre;
    private int valor;
    private int cantidad;

    public Pieza(String nombre, int valor, int cantidad) {
        this.nombre = nombre;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }
    public int getValor() {
        return valor;
    }
    public int getCantidad() {
        return cantidad;
    }
    public int addCantidad() {
        cantidad++;
        return cantidad;
    }
    public static ArrayList<Pieza> generaPieza(){
        ArrayList<Pieza> piezas = new ArrayList<Pieza>();
        piezas.add(new Pieza("Peon",1,0));
        piezas.add(new Pieza("Alfil",3,0));
        piezas.add(new Pieza("Caballo",3,0));
        piezas.add(new Pieza("Torre",5,0));
        piezas.add(new Pieza("Reina",9,0));
        piezas.add(new Pieza("Rey",100,0));
        return piezas;
    }
//    public ArrayList<Pieza> contCantidad(){
//        getCantidad();
//    }
}
