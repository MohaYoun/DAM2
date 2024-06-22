package com.example.ejerciciosjunio.ejerciciosPractJunio.ej1.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ejerciciosjunio.R;

import java.util.ArrayList;

public class AdapterEj1 extends RecyclerView.Adapter<AdapterEj1.ViewHolder>{
    private ArrayList<String> datos;

    public AdapterEj1(ArrayList<String> prueba){
        datos = new ArrayList<>();
        add(prueba);
    }

    public void add(ArrayList<String> prueba) {
        datos.addAll(prueba);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterEj1.ViewHolder onCreateViewHolder(@NonNull ViewGroup vistaGrupo, int viewType) {
        View view = LayoutInflater.from(vistaGrupo.getContext())
                .inflate(R.layout.row_ej1_practjun, vistaGrupo, false);

        return new AdapterEj1.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEj1.ViewHolder holder, int position) {
        holder.num.setText(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.ej1PrctJuntvNum);
        }
    }
}
