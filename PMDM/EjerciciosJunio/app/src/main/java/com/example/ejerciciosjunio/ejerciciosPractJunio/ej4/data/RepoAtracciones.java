package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoAtracciones {
    @GET("api/atracciones/")
    Call<List<Atracciones>> getAtracciones();
    @GET("api/atracciones/{id}")
    Call<Comentarios> getComentarios(@Path("id") int id);
}
