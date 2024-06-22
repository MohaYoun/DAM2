package com.example.ejerciciosjunio.ejerciciosPractJunio.ej1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import com.example.ejerciciosjunio.R;

import java.util.ArrayList;

public class ej1Main09042024 extends AppCompatActivity {
    public static final String NUMS_ARRAY = "1";
    public static final String TAM_ARRAY = "0";
    private static final int NUM_MAX = 101;
    EditText etNumero;
    Button btGenerar;

    int num_random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ej1_main09042024);

        etNumero = findViewById(R.id.ej1etNumero);
        btGenerar = findViewById(R.id.ej1btGenerar);

        btGenerar.setOnClickListener((v)->{
            int tamArry = Integer.parseInt(etNumero.getText().toString());

            ArrayList<String> nums = new ArrayList<>();

            for (int i = 0; i < tamArry; i++) {
                num_random = (int) (Math.random() * NUM_MAX);
                nums.add(String.valueOf(num_random));
            }
            Intent intent = new Intent(this, ej1Recep.class);

            intent.putExtra(NUMS_ARRAY, nums);
            intent.putExtra(TAM_ARRAY, String.valueOf(tamArry));
            startActivity(intent);
        });
    }
}