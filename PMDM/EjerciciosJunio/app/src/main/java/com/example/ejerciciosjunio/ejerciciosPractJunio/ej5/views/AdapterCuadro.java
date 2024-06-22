package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data.Cuadro;

import java.util.ArrayList;
import java.util.List;

public class AdapterCuadro extends RecyclerView.Adapter<AdapterCuadro.ViewHolder>{
    private List<Cuadro> datos;
    public AdapterCuadro (Cuadro prueba){
        datos = new ArrayList<>();
        add(prueba);
    }
    public void add(Cuadro prueba) {
        datos.add(prueba);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterCuadro.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cuadro_detalles, parent, false);

        return new AdapterCuadro.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCuadro.ViewHolder holder, int position) {
        Cuadro c = datos.get(position);
        holder.setInfo(c);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url;
        private TextView nomb;
        private TextView precio;
        private TextView fecha;
        private TextView tecnica;
        private TextView color;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            url = itemView.findViewById(R.id.ORDtvUrl);
            nomb = itemView.findViewById(R.id.ORDtvNombre);
            precio = itemView.findViewById(R.id.ORDtvPrecio);
            fecha = itemView.findViewById(R.id.ORDtvFecha);
            tecnica =itemView.findViewById(R.id.ORDtvTecnica);
            color = itemView.findViewById(R.id.ORDtvColor);
        }
        public void setInfo(Cuadro cuadro){
            url.setText(cuadro.getUrl().toString());
            nomb.setText(cuadro.getNombre().toString());
            precio.setText(String.valueOf(cuadro.getPrecio()).toString());
            fecha.setText(cuadro.getFecha().toString());
            tecnica.setText(cuadro.getTecnica().toString());
            color.setText(cuadro.getColor().toString());
        }
    }
}
