package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCuadros {
    private static ServiceCuadros instancia;
    private static RepoCuadros repo;
    private ServiceCuadros() {
        // Hago cosas
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(RepoCuadros.class);
    }
    public static RepoCuadros getRepoCuadro() {
        return repo;
    }
    public static ServiceCuadros getInstancia() {
        if (instancia == null) {
            instancia = new ServiceCuadros();
        }
        return instancia;
    }
}

