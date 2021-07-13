package com.example.mihuellasapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.MascotaPerdida;
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
            imagenMascota = itemView.findViewById(R.id.image_profile);
            Raza = itemView.findViewById(R.id.txt_raza_mascota_perdida);
            Edad = itemView.findViewById(R.id.txt_edad_mascota_perdida);
            Color = itemView.findViewById(R.id.txt_color_mascota_perdida);
            ver=itemView.findViewById(R.id.iv_ver_mascota_perdida);

        }
    }
}
