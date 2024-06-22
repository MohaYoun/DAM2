package com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.Actor;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej3.data.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class AdapterActores extends RecyclerView.Adapter<AdapterActores.ViewHolder> {

    // adapteractores
    private ArrayList<Pelicula> datos;

    public AdapterActores(List<Pelicula> listaPeliculas) {
        datos = new ArrayList<>();
        add(listaPeliculas);
    }

    public void add(List<Pelicula> listaPeliculas) {
        datos.addAll(listaPeliculas);
        notifyDataSetChanged();
    }


    @Override
    public AdapterActores.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_peliculas_apihappy, parent, false);
        return new AdapterActores.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterActores.ViewHolder holder, int position) {
        Pelicula pl = datos.get(position);
        holder.setInfo(pl);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    // el row
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView urlPelicula;
        private final TextView nombreActor;
        private final TextView urlActor;

        public ViewHolder(View itemView) {
            super(itemView);

            urlPelicula = itemView.findViewById(R.id.nombrePeliculas);
            nombreActor = itemView.findViewById(R.id.descripcionPeliculas);
            urlActor = itemView.findViewById(R.id.estrellasPeliculas);
        }

        // Método para configurar los datos en el ViewHolder
        public void setInfo(Pelicula pelicula) {
            // Configurar la información de los actores
            StringBuilder nombreActores = new StringBuilder();
            StringBuilder urlActores = new StringBuilder();
            StringBuilder urlPeliculas = new StringBuilder();

            for (Actor actor : pelicula.getActores()) {
                nombreActores.append(actor.getNombre()).append("\n");
                urlActores.append(actor.getUrl()).append("\n");
                urlPeliculas.append(actor.getPelicula()).append("\n");
            }
            nombreActor.setText(nombreActores.toString());
            urlPelicula.setText(urlPeliculas.toString());
            urlActor.setText(urlActores.toString());
        }
    }
}