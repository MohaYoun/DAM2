package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Comentario;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Comentarios;

import java.util.ArrayList;
import java.util.List;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.ViewHolder>{
    private List<Comentarios> datos;

    public AdapterComentario(Comentarios comentarios) {
        datos = new ArrayList<>();
        add(comentarios);
    }
    public void add(Comentarios dataSet){
        datos.add(dataSet);
        notifyDataSetChanged();
    }
    public List<Comentarios> getResults() {
        return datos;
    }
    @NonNull
    @Override
    public AdapterComentario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_atracciones, parent, false);

        return new AdapterComentario.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComentario.ViewHolder holder, int position) {
        Comentarios com = datos.get(position);
        holder.setInfoComentarios(com);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nomb;
        private TextView desc;
        private TextView ocup;
        private TextView coment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomb = itemView.findViewById(R.id.ej4tvNombre);
            desc = itemView.findViewById(R.id.ej4tvDescripcion);
            ocup = itemView.findViewById(R.id.ej4tvOcupantes);
            coment = itemView.findViewById(R.id.ej4tvComentario);
        }

        public void setInfoComentarios(Comentarios comentarios) {
            nomb.setText(comentarios.getNombre().toString());
            desc.setText(comentarios.getDescripcion().toString());
            ocup.setText(String.valueOf(comentarios.getOcupantes()).toString());
            StringBuilder comentarioAtraccion = new StringBuilder();

            for (Comentario comentario : comentarios.getComentarios()) {
                comentarioAtraccion.append(comentario.getTexto()).append("\n");
            }
            coment.setText(comentarioAtraccion.toString());
        }
    }
}
