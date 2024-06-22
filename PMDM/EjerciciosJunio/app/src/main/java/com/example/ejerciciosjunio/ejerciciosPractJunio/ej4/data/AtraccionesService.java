package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtraccionesService {
    private static AtraccionesService instancia;
    private static RepoAtracciones repoAtracciones;

    private AtraccionesService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        repoAtracciones=retrofit.create(RepoAtracciones.class);
    }

    public static RepoAtracciones getRepoAtracciones() {
        return repoAtracciones;
    }

    public static AtraccionesService getInstancia() {
        if(instancia == null){
            instancia = new AtraccionesService();
        }
        return instancia;
    }
}
