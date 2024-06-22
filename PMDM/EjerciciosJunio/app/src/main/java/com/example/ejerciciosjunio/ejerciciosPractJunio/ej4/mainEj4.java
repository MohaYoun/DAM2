package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Atracciones;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.AtraccionesService;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.views.AdapterAtracciones;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainEj4 extends AppCompatActivity {
    public static final String ATRACCION = "atraccion";
    private RecyclerView lista;
    private AdapterAtracciones adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ej4);

        lista=findViewById(R.id.ej4rvAtracciones);

        lista.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterAtracciones(new ArrayList<>());

        adapter.setClickListener(new AdapterAtracciones.OnItemClickListener() {
            @Override
            public void onItemClick(String url) {
                String atraccion = getId(url);
                Intent intent = new Intent(mainEj4.this, ComentariosActivity.class);
                intent.putExtra(ATRACCION, atraccion);
                startActivity(intent);
            }
        });

        lista.setAdapter(adapter);

        AtraccionesService ser = AtraccionesService.getInstancia();
        Call<List<Atracciones>> llamada = ser.getRepoAtracciones().getAtracciones();
        llamada.enqueue(new Callback<List<Atracciones>>() {
            @Override
            public void onResponse(Call<List<Atracciones>> call, Response<List<Atracciones>> response) {
                if (response.isSuccessful()) {
                    adapter.add(response.body());
                } else {
                    Toast.makeText(mainEj4.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Atracciones>> call, Throwable t) {
                Toast.makeText(mainEj4.this, "Fallo en la conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
    private String getId(String url) {
        String[] partes = url.split("/");
        String id = partes[partes.length - 1];
        return id;
    }
}