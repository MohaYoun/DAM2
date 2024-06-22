package com.example.ejerciciosjunio.ejerciciosPractJunio.ejArmamento.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciosjunio.R;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ejArmamento.data.Armamento;

import java.util.ArrayList;

public class ArmamentoAdapter extends RecyclerView.Adapter<ArmamentoAdapter.viewHolderArmas> {
    ArrayList<Armamento> datos;
    public ArmamentoAdapter(){
        datos = new ArrayList<Armamento>();
    }
    public void add(ArrayList<Armamento> nuevos){
        datos.addAll(nuevos);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ArmamentoAdapter.viewHolderArmas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_armamento, parent, false);

        return new ArmamentoAdapter.viewHolderArmas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmamentoAdapter.viewHolderArmas holder, int position) {
        Armamento ar = datos.get(position);
        holder.setinfo(ar);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class viewHolderArmas extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvPais;
        TextView tvTipo;
        public viewHolderArmas(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombArma);
            tvPais = itemView.findViewById(R.id.tvPaisArma);
            tvTipo = itemView.findViewById(R.id.tvTipoArma);
        }
        public void setinfo(Armamento armas){
            tvNombre.setText(armas.getNombre().toString());
            tvPais.setText(armas.getPais().toString());
            tvTipo.setText(armas.getTipo().toString());
        }
    }
}
