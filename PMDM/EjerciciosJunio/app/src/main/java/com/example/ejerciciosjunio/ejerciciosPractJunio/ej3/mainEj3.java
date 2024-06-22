package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.Pelicula;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.PeliculaService;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.views.AdapterPelicula;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainEj3 extends AppCompatActivity {
    public static final String PELICULA = "urlpelicula";
    private RecyclerView lista;
    private AdapterPelicula adpPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ej3);

        lista = findViewById(R.id.ej3rvPeliculas);
        lista.setLayoutManager(new LinearLayoutManager(this));
        adpPelicula = new AdapterPelicula(new ArrayList<>());

        adpPelicula.setClickListener(new AdapterPelicula.onItemClickListener() {
            @Override
            public void onItemClick(String url) {
                //Toast.makeText(mainEj3.this, "Has pulsado: "+position, Toast.LENGTH_SHORT).show();
                // String pel = String.valueOf(adpPelicula.getResults().get(position));

                String datos[] = url.split("/");
                int posicion = Integer.parseInt(datos[5]);
                //String pel = adpPelicula.getResults().get(posicion);
                //int pel = Integer.parseInt(String.valueOf(adpPelicula.getResults().get(position)));
                Intent intent = new Intent(mainEj3.this, mainActoresEj3.class);
                //intent.putExtra(mainEj3.PELICULA, pel);
                startActivity(intent);
            }
        });
        lista.setAdapter(adpPelicula);

        PeliculaService ser = PeliculaService.getInstancia();
        Call<List<Pelicula>> llamada = ser.getRepoPelicula().getPeliculas();
        llamada.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful()) {
                    adpPelicula.add(response.body());
                } else {
                    Toast.makeText(mainEj3.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Toast.makeText(mainEj3.this, "Fallo en la conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
}