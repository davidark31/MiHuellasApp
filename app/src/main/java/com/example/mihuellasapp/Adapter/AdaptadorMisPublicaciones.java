package com.example.mihuellasapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mihuellasapp.EditarPublicacionActivity;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.example.mihuellasapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorMisPublicaciones extends RecyclerView.Adapter<AdaptadorMisPublicaciones.ViewHolder> {

    private Context mContext;
    private List<Publicacion> lMisPublicaciones;

    public AdaptadorMisPublicaciones(Context mContext, List<Publicacion> lMisPublicaciones) {
        this.mContext = mContext;
        this.lMisPublicaciones = lMisPublicaciones;
    }


    @NonNull
        @Override
        public AdaptadorMisPublicaciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_publicaciones_usuario, parent, false);
            return new AdaptadorMisPublicaciones.ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMisPublicaciones.ViewHolder holder, int position) {

        Publicacion publicacion = lMisPublicaciones.get(position);
        holder.DescripcionPublicacion.setText(publicacion.getDescripcionPublicacion());
        holder.AnimalPublicacion.setText(publicacion.getAnimalPublicacion());
        Picasso.get().load(publicacion.getImageUrlPublicacion()).placeholder(R.mipmap.ic_launcher).into(holder.imagenMiPublicacion);
        holder.imagenMiPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), EditarPublicacionActivity.class);
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Id", publicacion.getIdPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Sexo", publicacion.getSexoPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Animal", publicacion.getAnimalPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color", publicacion.getColorPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color2", publicacion.getColor2Publicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Raza", publicacion.getRazaPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Edad", publicacion.getEdadPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Descripcion", publicacion.getDescripcionPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Tamaño", publicacion.getTamañoPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("IdDueño", publicacion.getIdDueñoPublicacion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("ImageUrl", publicacion.getImageUrlPublicacion()).apply();
                v.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return lMisPublicaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imagenMiPublicacion;
        public TextView DescripcionPublicacion;
        public TextView AnimalPublicacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMiPublicacion = itemView.findViewById(R.id.imagen_publicacion_usuario);
            DescripcionPublicacion = itemView.findViewById(R.id.descripcion_publicacion_usuario);
            AnimalPublicacion = itemView.findViewById(R.id.animal_publicacion_usuario);


        }
    }
}