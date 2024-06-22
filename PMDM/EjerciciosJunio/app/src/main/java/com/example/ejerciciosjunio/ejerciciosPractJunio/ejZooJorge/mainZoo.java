package com.example.ejerciciosjunio.ejerciciosPractJunio.ejZooJorge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ejZooJorge.data.Animal;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ejZooJorge.views.AnimalesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mainZoo extends AppCompatActivity {
    RecyclerView rv;
    AnimalesAdapter adapter;
    Button add, randOrd, alfOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_zoo);

        rv = findViewById(R.id.ut06_recycler_zoo);
        add = findViewById(R.id.ut06_btAdd);
        randOrd = findViewById(R.id.ut06_btDesordenar);
        alfOrd = findViewById(R.id.ut06_btOrdAlf);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimalesAdapter();

        rv.setAdapter(adapter);

        add.setOnClickListener((v)->{
            adapter.add(Animal.generarDatos());
        });
        randOrd.setOnClickListener((v)->{
            ArrayList <Animal> desOrdenado = new ArrayList<>(adapter.getResults());
            Collections.shuffle(desOrdenado);
            adapter.setResults(desOrdenado);
        });
        alfOrd.setOnClickListener((v)->{
//            ArrayList <Animal> ascOrdenar = new ArrayList<Animal>(adapter.getResults());
//            ascOrdenar.sort();
//            adapter.setResults(ascOrdenar);
        });
    }
}