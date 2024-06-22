package com.example.ejerciciosjunio.ejerciciosPractJunio.ej2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej2.Views.PiezaAdapter;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej2.data.Pieza;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class mainEj2 extends AppCompatActivity {
    private RecyclerView lista;
    private PiezaAdapter adpAjedrez;
    Button btGenerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ej2);

        lista = findViewById(R.id.ej2rvAjedrez);
        btGenerar = findViewById(R.id.ej2btGenerar);

        lista.setLayoutManager(new LinearLayoutManager(this));
        adpAjedrez = new PiezaAdapter(Pieza.generaPieza());
        lista.setAdapter(adpAjedrez);

        adpAjedrez.setClickListener(new PiezaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adpAjedrez.getResults().get(position).addCantidad();
            }
        });

        btGenerar.setOnClickListener((v)->{
            ArrayList<Pieza> cantPiezas = new ArrayList<>(adpAjedrez.getResults());

        });
    }
}