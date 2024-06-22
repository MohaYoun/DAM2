package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoCuadros {
    @GET("api/cuadro/{id}")
    Call<Cuadro> getCuadro(@Path("id") int id);
    @GET("api/cuadro/")
    Call<List<Cuadros>> getCuadros();
}
