package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data.Cuadros;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.views.AdapterCuadro;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.views.AdapterCuadros;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.views.ViewModelCuadros;

import java.util.ArrayList;

public class MainActivityEj5 extends AppCompatActivity {

    private RecyclerView lista, listaCuadro;
    private AdapterCuadros adapter;
    private AdapterCuadro adapterCuadro;
    ProgressBar pbCarga;
    EditText etId;
    Button btGenerar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ej5);

        lista = findViewById(R.id.ej5rvCuadros);
        listaCuadro = findViewById(R.id.ej5rvCuadro);
        pbCarga = findViewById(R.id.ej5pbCarga);
        etId = findViewById(R.id.ej5etIdCuadro);
        btGenerar = findViewById(R.id.ej5btGenerar);

        adapter = new AdapterCuadros(new ArrayList<>());
        pbCarga.setVisibility(View.INVISIBLE);
        listaCuadro.setVisibility(View.INVISIBLE);

        lista.setLayoutManager(new LinearLayoutManager(this));
        listaCuadro.setLayoutManager(new LinearLayoutManager(this));
        ViewModelCuadros mvvm = new ViewModelProvider(this).get(ViewModelCuadros.class);

        pbCarga.setVisibility(View.VISIBLE);
        mvvm.cargaCuadros();
        mvvm.getCuadros().observe(this, cuadros -> {
            pbCarga.setVisibility(View.INVISIBLE);
//            adapter = new AdapterCuadros(cuadros);
            adapter.add(cuadros);
            lista.setAdapter(adapter);
        });

        btGenerar.setOnClickListener((v)->{
            pbCarga.setVisibility(View.VISIBLE);
            lista.setVisibility(View.INVISIBLE);
            listaCuadro.setVisibility(View.VISIBLE);
            mvvm.cargaCuadro(Integer.parseInt(etId.getText().toString().trim()));
            mvvm.getCuadro().observe(this, cuadro -> {
                pbCarga.setVisibility(View.INVISIBLE);
//            adapter = new AdapterCuadros(cuadros);
                adapterCuadro = new AdapterCuadro(cuadro);
                listaCuadro.setAdapter(adapterCuadro);
            });
        });


    }
}