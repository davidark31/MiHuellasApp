package com.example.mihuellasapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mihuellasapp.MascotaPerdidaActivity;
import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.PerfilMascotaActivity;
import com.example.mihuellasapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorMascotas extends RecyclerView.Adapter<AdaptadorMascotas.ViewHolder> {

    private Context mContext;
    private List<Mascota> lMascotas;

    public AdaptadorMascotas(Context mContext, List<Mascota> lMascotas) {
        this.mContext = mContext;
        this.lMascotas = lMascotas;
    }

    @NonNull
    @Override
    public AdaptadorMascotas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_mascotas_usuario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMascotas.ViewHolder holder, int position) {

        Mascota mascota = lMascotas.get(position);
        holder.Nombre.setText(mascota.getNombre());
        if (mascota.getEstado().equals("En casa")) {
            holder.estado.setText(mascota.getEstado());
            holder.estado.setTextColor(Color.parseColor("#3CAD4C"));

        } else {
            if (mascota.getEstado().equals("Perdida")) {
                holder.estado.setText(mascota.getEstado());
                holder.estado.setTextColor(Color.parseColor("#E83030"));
            }else{
                holder.estado.setText(mascota.getEstado());
                holder.estado.setTextColor(Color.parseColor("#F4FE27"));
            }

        }
        holder.estado.setText(mascota.getEstado());
        Picasso.get().load(mascota.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.imagenMascota);
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PerfilMascotaActivity.class);
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Id", mascota.getId()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Nombre", mascota.getNombre()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Sexo", mascota.getSexo()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Animal", mascota.getAnimal()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color", mascota.getColor()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color2", mascota.getColor2()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Raza", mascota.getRaza()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Edad", mascota.getEdad()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Estado", mascota.getEstado()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Descripcion", mascota.getDescripcion()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Tamaño", mascota.getTamaño()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("IdDueño", mascota.getIdDueño()).apply();
                v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("ImageUrl", mascota.getImageUrl()).apply();
                v.getContext().startActivity(intent);

            }
        });

        holder.perdida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder del = new AlertDialog.Builder(v.getContext());
                del.setMessage("Estas seguro de cambiar estado a perdida?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(v.getContext(), MascotaPerdidaActivity.class);
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Id", mascota.getId()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Nombre", mascota.getNombre()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Sexo", mascota.getSexo()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Animal", mascota.getAnimal()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color", mascota.getColor()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Color2", mascota.getColor2()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Raza", mascota.getRaza()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Edad", mascota.getEdad()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Estado", mascota.getEstado()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Descripcion", mascota.getDescripcion()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Tamaño", mascota.getTamaño()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("IdDueño", mascota.getIdDueño()).apply();
                        v.getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("ImageUrl", mascota.getImageUrl()).apply();
                        v.getContext().startActivity(intent);

                    }
                });

                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
                del.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return lMascotas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imagenMascota;
        public TextView Nombre;
        public TextView estado;
        public ImageButton perdida, editar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMascota = itemView.findViewById(R.id.image_profile);
            Nombre = itemView.findViewById(R.id.nombre_mascota);
            estado = itemView.findViewById(R.id.raza_mascota);
            perdida = itemView.findViewById(R.id.ib_perdida);
            editar = itemView.findViewById(R.id.ib_editar);

        }
    }
}
