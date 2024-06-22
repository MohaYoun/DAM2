package com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Atracciones;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Comentario;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej4.data.Comentarios;

import java.util.ArrayList;
import java.util.List;


public class AdapterAtracciones extends RecyclerView.Adapter<AdapterAtracciones.ViewHolder>{
    private List<Atracciones> datos;

    public AdapterAtracciones(List<Atracciones> atracciones) {
        datos = new ArrayList<>();
        add(atracciones);
    }
    public void add(List<Atracciones> dataSet){
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }
    public List<Atracciones> getResults() {
        return datos;
    }
    public interface OnItemClickListener{
        void onItemClick(String url);
    }
    private AdapterAtracciones.OnItemClickListener listener;
    public void setClickListener(AdapterAtracciones.OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public AdapterAtracciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_atracciones, parent, false);

        return new AdapterAtracciones.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAtracciones.ViewHolder holder, int position) {
        Atracciones a = datos.get(position);
        holder.setInfo(a);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView url;
        private TextView nomb;
        private TextView desc;
        private TextView ocup;
        private TextView coment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.ej4tvUrl);
            nomb = itemView.findViewById(R.id.ej4tvNombre);
            desc = itemView.findViewById(R.id.ej4tvDescripcion);
            ocup = itemView.findViewById(R.id.ej4tvOcupantes);
            coment = itemView.findViewById(R.id.ej4tvComentario);
            itemView.setOnClickListener(this);
        }
        public void setInfo(Atracciones atracciones){
            url.setText(atracciones.getUrl().toString());
            nomb.setText(atracciones.getNombre().toString());
            desc.setText(atracciones.getDescripcion().toString());
            ocup.setText(String.valueOf(atracciones.getOcupantes()).toString());
        }
        public void setInfoComentarios(Comentarios comentarios){
            nomb.setText(comentarios.getNombre().toString());
            desc.setText(comentarios.getDescripcion().toString());
            ocup.setText(String.valueOf(comentarios.getOcupantes()).toString());
            StringBuilder comentarioAtraccion = new StringBuilder();

            for (Comentario comentario : comentarios.getComentarios()) {
                comentarioAtraccion.append(comentario.getTexto()).append("\n");
            }
            coment.setText(comentarioAtraccion.toString());
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(datos.get(position).getUrl());
                }
            }
        }
    }
}
