package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data.Cuadros;

import java.util.ArrayList;
import java.util.List;

public class AdapterCuadros extends RecyclerView.Adapter<AdapterCuadros.ViewHolder>{
    private List<Cuadros> datos;
    public AdapterCuadros(List<Cuadros> cuadros) {
        datos = new ArrayList<>();
        add(cuadros);
    }
    public void add(List<Cuadros> dataSet){
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }
    public List<Cuadros> getResults() {
        return datos;
    }
    @NonNull
    @Override
    public AdapterCuadros.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cuadros, parent, false);

        return new AdapterCuadros.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCuadros.ViewHolder holder, int position) {
        Cuadros c = datos.get(position);
        holder.setInfo(c);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView nom;
        private TextView precio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.exOrd2tvId);
            nom = itemView.findViewById(R.id.exOrd2tvNombre);
            precio = itemView.findViewById(R.id.exOrd2tvPrecio);
        }
        public void setInfo(Cuadros cuadros){
            id.setText(String.valueOf(cuadros.getId()).toString());
            nom.setText(cuadros.getNombre().toString());
            precio.setText(String.valueOf(cuadros.getPrecio()).toString());
        }
    }
}
