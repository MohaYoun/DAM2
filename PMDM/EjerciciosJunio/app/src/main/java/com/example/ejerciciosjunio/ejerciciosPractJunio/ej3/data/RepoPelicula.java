package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoPelicula {
    @GET("api/peliculas_related/")
    Call<List<Pelicula>> getPeliculas();
    @GET("api/peliculas_related/{id}")
    Call<Pelicula> getActores(@Path("id") int id);
}
