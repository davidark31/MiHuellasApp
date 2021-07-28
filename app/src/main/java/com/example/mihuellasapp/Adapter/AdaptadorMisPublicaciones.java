package com.example.mihuellasapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMisPublicaciones.ViewHolder holder, int position) {

        Publicacion publicacion = lMisPublicaciones.get(position);
        holder.Descripcion.setText(publicacion.getDescripcion());
        holder.Animal.setText(publicacion.getAnimal());
        Picasso.get().load(publicacion.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.ImageUrl);
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), EditarPublicacionActivity.class);
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Id", publicacion.getId()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Sexo", publicacion.getSexo()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Animal", publicacion.getAnimal()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color", publicacion.getColor()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color2", publicacion.getColor2()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Raza", publicacion.getRaza()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Edad", publicacion.getEdad()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Descripcion", publicacion.getDescripcion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Tamaño", publicacion.getTamano()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("IdDueno", publicacion.getIdPublicador()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("ImageUrl", publicacion.getImageUrl()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Latitud", String.valueOf(publicacion.getLatitud())).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Longitud", String.valueOf(publicacion.getLongitud())).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Fecha", publicacion.getFecha()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Contacto", publicacion.getContacto()).apply();
                v.getContext().startActivity(intent);


            }
        });


    }



    @Override
    public int getItemCount() {
        return lMisPublicaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView ImageUrl;
        public TextView Descripcion;
        public TextView Animal;
        public ImageButton editar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageUrl = itemView.findViewById(R.id.imagen_publicacion_usuario);
            Animal = itemView.findViewById(R.id.descripcion_publicacion_usuario);
            Descripcion = itemView.findViewById(R.id.animal_publicacion_usuario);
            editar = itemView.findViewById(R.id.ib_editar_publicacion);

        }
    }
}