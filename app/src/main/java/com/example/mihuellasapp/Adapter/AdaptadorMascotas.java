package com.example.mihuellasapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.PerfilMascotaActivity;
import com.example.mihuellasapp.R;
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
        holder.estado.setText(mascota.getEstado());
        Picasso.get().load(mascota.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.imagenMascota);
        holder.imagenMascota.setOnClickListener(new View.OnClickListener() {
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
          /*        mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit().putString("Mascotaid", mascota.getId()).apply();

                 ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                       .replace(R.id.fragment_container, new PerfilMascotaFragment()).commit();
*/

                //aqui implementamos la edicion para agregar
    /*            int pos = Integer.parseInt(v.getTag().toString());
                Mascota ma = lMascotas.get(pos);
                final Dialog dialogo = new Dialog();
                dialogo.setTitle("Editar Mascota");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.perfil_mascota);
                dialogo.show();

                EditText nom = (EditText) dialogo.findViewById(R.id.txt_nombre_mascota);
                Spinner ani = (Spinner) dialogo.findViewById(R.id.spn_animal);
                Spinner sex = (Spinner) dialogo.findViewById(R.id.spn_sexo_perfilMascota);
                Spinner col = (Spinner) dialogo.findViewById(R.id.spn_color);
                Spinner col2 = (Spinner) dialogo.findViewById(R.id.spn_color2);
                Spinner ra = (Spinner) dialogo.findViewById(R.id.spn_raza);
                Spinner tam = (Spinner) dialogo.findViewById(R.id.spn_tamaño);
                RadioButton cachorro = (RadioButton) dialogo.findViewById(R.id.rdb_cachorro);
                RadioButton adulto = (RadioButton) dialogo.findViewById(R.id.rdb_adulto);
                RadioButton anciano = (RadioButton) dialogo.findViewById(R.id.rdb_anciano);
                EditText desc = (EditText) dialogo.findViewById(R.id.txt_descripcion);

                ImageButton borrar = (ImageButton) dialogo.findViewById(R.id.btn_borrar);

                Button edit = (Button) dialogo.findViewById(R.id.btn_editar_mascota);
                Button cancelar = (Button) dialogo.findViewById(R.id.btn_cancelar);

                //recuperamos los datos que estan almacenados en la base de datos
                nom.setText(ma.getNombre());
                ani.setSelection(selectValue(ani, ma.getAnimal()));
                sex.setSelection(selectValue(sex, ma.getSexo()));
                col.setSelection(selectValue(col, ma.getColor()));
                col2.setSelection(selectValue(col2, ma.getColor2()));
                ra.setSelection(selectValue(ra, ma.getRaza()));


                desc.setText(ma.getDescripcion());

                borrar.setOnClickListener(view1 -> {
                    int posi = Integer.parseInt(v.getTag().toString());

                    //dialogo para confirmar si o no
                    AlertDialog.Builder del = new AlertDialog.Builder(a);
                    del.setMessage("Estas seguro de eliminar registro Mascota?");
                    del.setCancelable(false);
                    del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //   dao.eliminar(getId());
                            // lista = dao.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        }

                    });

                    del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                        }
                    });
                    del.show();
                });


                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            nom.getText().toString(),
                                    ani.getSelectedItem().toString(),
                                    sex.getSelectedItem().toString(),
                                    col.getSelectedItem().toString(),
                                    col2.getSelectedItem().toString(),
                                    ra.getSelectedItem().toString(),
                                    tam.getSelectedItem().toString(),
                                    Integer.parseInt(ed.getText().toString()),
                                    desc.getText().toString());
                            if (dao.editar(ma)) {
                                lista = dao.verTodos();
                                Toast.makeText(a, "Editado", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                dialogo.dismiss();
                            }
                        } catch (Exception e) {
                            Toast.makeText(a, "Error no se guardo", Toast.LENGTH_SHORT).show();
                        }
                    }

                });


           */
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMascota = itemView.findViewById(R.id.image_profile);
            Nombre = itemView.findViewById(R.id.nombre_mascota);
            estado = itemView.findViewById(R.id.raza_mascota);


        }
    }
}
