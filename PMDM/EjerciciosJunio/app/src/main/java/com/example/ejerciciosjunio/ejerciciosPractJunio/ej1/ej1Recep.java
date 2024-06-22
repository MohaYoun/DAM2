package com.example.ejerciciosjunio.ejerciciosPractJunio.ej1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;


import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej1.view.AdapterEj1;

import java.io.Serializable;
import java.util.ArrayList;

public class ej1Recep extends AppCompatActivity implements Serializable {
    TextView tvPrueba, tvMax, tvMed, tvMin;
    private RecyclerView rv;

    private AdapterEj1 adp;
    private ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ej1_recep);

        tvPrueba = findViewById(R.id.ej1tvPruba);
        rv=findViewById(R.id.ej1rvLista);
        tvMax = findViewById(R.id.ej1tvMax);
        tvMed = findViewById(R.id.ej1tvMedia);
        tvMin = findViewById(R.id.ej1tvMin);

        String tamArray = getIntent().getStringExtra(ej1Main09042024.TAM_ARRAY);
        int tamanArr = Integer.parseInt(tamArray);

        lista = (ArrayList<String>)getIntent().getSerializableExtra(ej1Main09042024.NUMS_ARRAY);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adp = new AdapterEj1(lista);
        rv.setAdapter(adp);

        tvPrueba.setText("");
//        tvPrueba.setText(lista.toString();
//        for (int i = 0; i < tamanArr; i++) {
//           tvPrueba.append(lista.get(i)+"\n");
//        }
        int max=0, med=0, min=0;
        for (int i = 0; i < tamanArr; i++) {
            if(Integer.parseInt(lista.get(i)) > max){
                max = Integer.parseInt(lista.get(i));
            }
        }
        for (int i = 0; i < tamanArr; i++) {
            med += Integer.parseInt(lista.get(i));
        }
        int media = (med/tamanArr);
        min = max;
        for (int i = 0; i < Integer.parseInt(tamArray); i++) {
            if(Integer.parseInt(lista.get(i)) < min){
                min = Integer.parseInt(lista.get(i));
            }
        }
        tvMax.setText(String.valueOf(max));
        tvMed.setText(String.valueOf(media));
        tvMin.setText(String.valueOf(min));
    }
}