package com.example.mihuellasapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mihuellasapp.Modelo.MascotaPerdida;
import com.example.mihuellasapp.PerfilMascotaActivity;
import com.example.mihuellasapp.PublicacionMascotaPerdidaActivity;
import com.example.mihuellasapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorMascotasPerdidas extends RecyclerView.Adapter<AdaptadorMascotasPerdidas.ViewHolder>  {

    private Context mContext;
    private List<MascotaPerdida> mascotaPerdidaList;

    public AdaptadorMascotasPerdidas(Context mContext, List<MascotaPerdida> mascotaPerdidaList) {
        this.mContext = mContext;
        this.mascotaPerdidaList = mascotaPerdidaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_mascotas_perdidas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AdaptadorMascotasPerdidas.ViewHolder holder, int position) {
        MascotaPerdida mascotaP = mascotaPerdidaList.get(position);
        holder.Raza.setText(mascotaP.getRaza());
        holder.Edad.setText(mascotaP.getEdad());
        holder.Color.setText(mascotaP.getColor()+" con "+mascotaP.getColor2());
        Picasso.get().load(mascotaP.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.imagenMascota);
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PublicacionMascotaPerdidaActivity.class);
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Id", mascotaP.getId()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Nombre", mascotaP.getNombre()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Sexo", mascotaP.getSexo()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Animal", mascotaP.getAnimal()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color", mascotaP.getColor()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color2", mascotaP.getColor2()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Raza", mascotaP.getRaza()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Edad", mascotaP.getEdad()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Estado", mascotaP.getEstado()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Descripcion", mascotaP.getDescripcion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Tamaño", mascotaP.getTamaño()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("IdDueño", mascotaP.getIdDueño()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("ImageUrl", mascotaP.getImageUrl()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("IDMascota", mascotaP.getiDMascota()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("DescripcionSuceso", mascotaP.getDescripcionSuceso()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Fecha", mascotaP.getFecha()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Latitud", mascotaP.getLatitud()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Longitud", mascotaP.getLongitud()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Recompensa", mascotaP.getRecompensa()).apply();
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotaPerdidaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imagenMascota;
        public TextView Raza;
        public TextView Edad;
        public TextView Color;
        public ImageView ver;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMascota = itemView.findViewById(R.id.img_publi);
            Raza = itemView.findViewById(R.id.txt_raza_mascota_perdida);
            Edad = itemView.findViewById(R.id.txt_edad_mascota_perdida);
            Color = itemView.findViewById(R.id.txt_color_mascota_perdida);
            ver=itemView.findViewById(R.id.iv_ver_mascota_perdida);

        }
    }
}
