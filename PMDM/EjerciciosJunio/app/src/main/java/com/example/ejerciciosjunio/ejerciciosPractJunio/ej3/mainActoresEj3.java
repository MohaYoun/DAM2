package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.Actor;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.Pelicula;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.PeliculaService;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.views.AdapterActores;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.views.AdapterPelicula;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainActoresEj3 extends AppCompatActivity {

    private AdapterActores adpActores;
    private RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_actores_ej3);

        lista = findViewById(R.id.ej3rvActores);
        lista.setLayoutManager(new LinearLayoutManager(this));


        lista.setAdapter(adpActores);

        String linea = getIntent().getStringExtra(mainEj3.PELICULA); // Obtener el ID del intent
        int id = Integer.parseInt(linea)+1;

        PeliculaService ser = PeliculaService.getInstancia();
        Call<Pelicula> llamada = ser.getRepoPelicula().getActores(id);
        llamada.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    Pelicula p = response.body();
                    adpActores = new AdapterActores((List<Pelicula>) p);
                } else {
                    Toast.makeText(mainActoresEj3.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {

            }
        });
    }
}