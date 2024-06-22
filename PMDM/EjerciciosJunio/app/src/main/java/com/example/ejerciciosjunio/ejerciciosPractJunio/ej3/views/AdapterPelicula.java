package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class AdapterPelicula extends RecyclerView.Adapter<AdapterPelicula.ViewHolder> {

    private ArrayList<Pelicula> datos;

    public AdapterPelicula(List<Pelicula> listaPeliculas) {
        datos = new ArrayList<>();
        add(listaPeliculas);
    }

    public void add(List<Pelicula> listaPeliculas) {
        datos.addAll(listaPeliculas);
        notifyDataSetChanged();
    }

    public List<Pelicula> getResults() {
        return datos;
    }
//    public void setResults(List<Pelicula> results) {
//        this.datos = (ArrayList<Pelicula>) results;
//        notifyDataSetChanged();
//    }

    // Interface para manejar los clics en los elementos del RecyclerView
    public interface onItemClickListener{
        void onItemClick(String url);
    }
    private onItemClickListener listener;
    public void setClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public AdapterPelicula.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_peliculas_apihappy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterPelicula.ViewHolder holder, int position) {
        Pelicula pl = datos.get(position);
        holder.setInfo(pl);

        // Configuración del clic en el elemento del RecyclerView
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    // Llamada al método onItemClick del listener
//                    int posicion = holder.getAdapterPosition();
//                    listener.onItemClick(posicion);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    // el row
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView nombrePeliculas;
        private final TextView descripcionPeliculas;
        private final TextView estrellasPeliculas;
        public ViewHolder(View itemView) {
            super(itemView);

            nombrePeliculas = itemView.findViewById(R.id.nombrePeliculas);
            descripcionPeliculas = itemView.findViewById(R.id.descripcionPeliculas);
            estrellasPeliculas = itemView.findViewById(R.id.estrellasPeliculas);
            itemView.setOnClickListener(this);
        }

        // Método para configurar los datos en el ViewHolder
        public void setInfo(Pelicula pelicula) {
            nombrePeliculas.setText(pelicula.getNombre().toString());
            descripcionPeliculas.setText(pelicula.getDescripcion().toString());
            estrellasPeliculas.setText(pelicula.getEstrellas().toString());
        }
        @Override
        public void onClick(View v) {
//            if (listener != null) {
//                // Llamada al método onItemClick del listener
//
//                listener.onItemClick(getAdapterPosition());
//            }
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                //listener.onItemClick(datos.get(position));
            }
        }
    }
}
