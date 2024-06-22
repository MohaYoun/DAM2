package com.example.ejerciciosjunio.ejerciciosPractJunio.ej2.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej2.data.Pieza;

import java.util.ArrayList;
import java.util.List;

public class PiezaAdapter extends RecyclerView.Adapter<PiezaAdapter.ViewHolder>{
    private List<Pieza> datos;
    public PiezaAdapter(List<Pieza> piezas) {
        datos = new ArrayList<>();
        add(piezas);
    }
    public void add(List<Pieza> dataSet){
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }
    public List<Pieza> getResults() {
        return datos;
    }
    public void setResults(List<Pieza> results) {
        this.datos = results;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    private OnItemClickListener listener;
    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PiezaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup vistaGrupo, int viewType) {
        View view = LayoutInflater.from(vistaGrupo.getContext())
                .inflate(R.layout.row_tablero, vistaGrupo, false);

        return new PiezaAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PiezaAdapter.ViewHolder holder, int position) {
        Pieza p = datos.get(position);
        holder.setInfo(p);
    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cant;
        private TextView nomb;
        private TextView valor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomb = itemView.findViewById(R.id.ej2tvNombre);
            valor = itemView.findViewById(R.id.ej2tvValor);
            cant = itemView.findViewById(R.id.ej2tvCantidad);

            //itemView.setOnClickListener(this);
            cant.setOnClickListener(this);
        }

        public void setInfo(Pieza piezas) {
            nomb.setText(piezas.getNombre().toString());
            valor.setText(String.valueOf(piezas.getValor()));
            cant.setText(String.valueOf(piezas.getCantidad()));
            cant.setText("");
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            }

            cant.append("+");
            datos.get(getAdapterPosition()).addCantidad();
        }
    }
}