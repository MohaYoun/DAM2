package com.example.ejerciciosjunio.ejerciciosPractJunio.ejZooJorge.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej2.data.Pieza;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ejZooJorge.data.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AnimalesAdapter extends RecyclerView.Adapter<AnimalesAdapter.viewHolderAnimales>{
    ArrayList<Animal> datos;

    public AnimalesAdapter(){
        //datos = Animal.generarDatos(); // Simula acceso a API o base de datos
        datos = new ArrayList<Animal>();
    }
    public List<Animal> getResults(){
        return datos;
    }
    public void setResults(ArrayList<Animal> results) {
        this.datos = results;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AnimalesAdapter.viewHolderAnimales onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_zoo, parent, false);

        return new viewHolderAnimales(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalesAdapter.viewHolderAnimales holder, int position) {
        Animal a = datos.get(position);
        holder.pinta(a);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
    public void add(ArrayList<Animal> nuevos) {
        datos.addAll(nuevos);
        notifyDataSetChanged();
    }

    public class viewHolderAnimales extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView tipo;
        TextView color;
        public viewHolderAnimales(@NonNull View vista) {
            super(vista);
            nombre = vista.findViewById(R.id.ut06zoo_nombre);
            color = vista.findViewById(R.id.ut06zoo_color);
            tipo = vista.findViewById(R.id.ut06zoo_tipo);
        }
        public void pinta(Animal animal){
            nombre.setText(animal.getNombre());
            color.setText(animal.getColor());
            tipo.setText(animal.getTipo());
        }
    }
}
