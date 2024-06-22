package com.example.ejerciciosjunio.ejerciciosPractJunio.ejArmamento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ejArmamento.data.Armamento;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ejArmamento.views.ArmamentoAdapter;

public class mainArmamento extends AppCompatActivity {
    Button btGenerar;
    RecyclerView rv;
    ArmamentoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_armamento);

        btGenerar = findViewById(R.id.btGenArm);
        rv = findViewById(R.id.rvArmamento);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArmamentoAdapter();

        rv.setAdapter(adapter);
         btGenerar.setOnClickListener((v)->{
             adapter.add(Armamento.generaArmamento());
         });
    }
}