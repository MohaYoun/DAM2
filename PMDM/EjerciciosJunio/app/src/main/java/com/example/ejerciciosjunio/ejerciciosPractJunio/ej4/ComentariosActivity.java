package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Atracciones;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.AtraccionesService;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Comentarios;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.views.AdapterAtracciones;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.views.AdapterComentario;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ex2.ex2ord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentariosActivity extends AppCompatActivity {
    private RecyclerView lista;
    private AdapterComentario adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        lista = findViewById(R.id.ej4rvComentario);
        lista.setLayoutManager(new LinearLayoutManager(this));
        String linea = getIntent().getStringExtra(mainEj4.ATRACCION);

//        String com[] = linea.split("http://192.168.56.101:8000/api/atracciones/");
//        String linea2 = com[1];
//        String id = linea2.substring(0,1);


        AtraccionesService ser = AtraccionesService.getInstancia();
        Call<Comentarios> llamada = ser.getRepoAtracciones().getComentarios(Integer.parseInt(linea));
        llamada.enqueue(new Callback<Comentarios>() {
            @Override
            public void onResponse(Call<Comentarios> call, Response<Comentarios> response) {
                if (response.isSuccessful()) {
                    Comentarios comentarios = response.body();
                    adapter = new AdapterComentario(comentarios);
                    lista.setAdapter(adapter);
                } else {
                    Toast.makeText(ComentariosActivity.this, "Error al cargar los datos.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Comentarios> call, Throwable t) {
                Toast.makeText(ComentariosActivity.this, "Fallo en la conexion.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}