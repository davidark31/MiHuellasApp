package com.example.mihuellasapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorMascotas extends RecyclerView.Adapter<AdaptadorMascotas.ViewHolder> {

    private List<Mascota> lMascotas;

    public AdaptadorMascotas(List<Mascota> lMascotas) {
        this.lMascotas = lMascotas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_mascotas_usuario, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMascotas.ViewHolder holder, int position) {

        Mascota mascota = lMascotas.get(position);
        holder.Nombre.setText(mascota.getNombre());
        holder.estado.setText(mascota.getEstado());
        Picasso.get().load(mascota.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.imagenMascota);

    }

    @Override
    public int getItemCount() {return lMascotas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imagenMascota;
        public TextView Nombre;
        public TextView estado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMascota = itemView.findViewById(R.id.image_profile);
            Nombre = itemView.findViewById(R.id.nombre_mascota);
            estado = itemView.findViewById(R.id.raza_mascota);


        }
    }
}
