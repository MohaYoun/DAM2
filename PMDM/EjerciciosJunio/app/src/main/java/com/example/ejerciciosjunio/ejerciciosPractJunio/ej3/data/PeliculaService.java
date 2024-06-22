package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaService {
    private static PeliculaService instancia;
    private static RepoPelicula repoPeliculas;

    private PeliculaService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        repoPeliculas=retrofit.create(RepoPelicula.class);
    }

    public static RepoPelicula getRepoPelicula() {
        return repoPeliculas;
    }

    public static PeliculaService getInstancia() {
        if(instancia == null){
            instancia = new PeliculaService();
        }
        return instancia;
    }
}
