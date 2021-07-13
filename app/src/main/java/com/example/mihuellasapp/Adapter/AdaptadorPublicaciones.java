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

public class AdaptadorPublicaciones extends RecyclerView.Adapter<AdaptadorPublicaciones.ViewHolder> {

    private Context mContext;
    private List<Publicacion> lPublicaciones;

    public AdaptadorPublicaciones(Context mContext, List<Publicacion> lPublicaciones) {
        this.mContext = mContext;
        this.lPublicaciones = lPublicaciones;
    }

    @NonNull
    @Override
    public AdaptadorPublicaciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_publicaciones_comunidad, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPublicaciones.ViewHolder holder, int position) {

        Publicacion publicacion = lPublicaciones.get(position);
        holder.Descripcion.setText(publicacion.getRaza());
        holder.Animal.setText(publicacion.getAnimal());
        holder.editar.setText(publicacion.getDescripcion());
        Picasso.get().load(publicacion.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.ImageUrl);
        /*
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
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("IdDueño", publicacion.getIdDueno()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("ImageUrl", publicacion.getImageUrl()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Latitud", String.valueOf(publicacion.getLatitud())).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Longitud", String.valueOf(publicacion.getLongitud())).apply();
                v.getContext().startActivity(intent);



            }
        });
 */


    }


    @Override
    public int getItemCount() {
        return lPublicaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView ImageUrl;
        public TextView Descripcion;
        public TextView Animal;
        public TextView editar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageUrl = itemView.findViewById(R.id.img_publi);
            Animal = itemView.findViewById(R.id.txt_raza_publi);
            Descripcion = itemView.findViewById(R.id.txt_fecha_publi);
            editar = itemView.findViewById(R.id.txt_descripcion_publi);

        }
    }
}
