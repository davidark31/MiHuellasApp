package com.example.mihuellasapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptor extends BaseAdapter {
    ArrayList<Mascota> lista;
    Mascota ma;
    Activity a;
    String id = "";

    public Adaptor(Activity a, ArrayList<Mascota> lista) {
        this.lista = lista;
        this.a = a;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        ma = lista.get(i);
        return ma;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public int posicionSpinner(Spinner spi, String dato) {
        int posicion = 0;
        for (int i = 0; i < spi.getAdapter().getCount(); i++) {
            if (spi.getItemAtPosition(i).equals(dato)) {
                posicion = i;
            }
        }
        return posicion;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.lista_mascotas_usuario, null);
        }
        ma = lista.get(position);

        TextView nombre = v.findViewById(R.id.nombre_mascota);

        ImageButton ver = v.findViewById(R.id.foto_mascota);


        nombre.setText(ma.getNombre());
        ver.setTag(position);
/*
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui implementamos la edicion para agregar
                int pos = Integer.parseInt(view.getTag().toString());

                final Dialog dialogo = new Dialog(a);
                dialogo.setTitle("Editar Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.activity_perfil_mascota);
                dialogo.show();

                final TextView nombre = (TextView) dialogo.findViewById(R.id.tv_nombre);
                final TextView animal = (TextView) dialogo.findViewById(R.id.tv_animal);
                final TextView sexo = (TextView) dialogo.findViewById(R.id.tv_sexo);
                final TextView color = (TextView) dialogo.findViewById(R.id.tv_color);
                final TextView color2 = (TextView) dialogo.findViewById(R.id.tv_color2);
                final TextView raza = (TextView) dialogo.findViewById(R.id.tv_raza);
                final TextView tamaño = (TextView) dialogo.findViewById(R.id.tv_tamaño);
                final TextView edad = (TextView) dialogo.findViewById(R.id.tv_edad);
                final TextView descripcion = (TextView) dialogo.findViewById(R.id.tv_descripcion);

                ImageButton editar = (ImageButton) dialogo.findViewById(R.id.ib_editar);
                ImageButton borrar = (ImageButton) dialogo.findViewById(R.id.btn_borrar);

                ma = lista.get(pos);
                setId(ma.getId());

                //recuperamos los datos que estan almacenados en la base de datos
                nombre.setText(ma.getNombre());
                animal.setText(ma.getAnimal());
                sexo.setText(ma.getSexo());
                color.setText(ma.getColor());
                color2.setText(ma.getColor2());
                raza.setText(ma.getRaza());
                tamaño.setText(ma.getTamaño());
                edad.setText("" + ma.getEdad());
                descripcion.setText(ma.getDescripcion());

                borrar.setOnClickListener(view1 -> {
                    int posi = Integer.parseInt(view.getTag().toString());
                    ma = lista.get(posi);
                    setId(ma.getId());
                    //dialogo para confirmar si o no
                    AlertDialog.Builder del = new AlertDialog.Builder(a);
                    del.setMessage("Estas seguro de eliminar registro Mascota?");
                    del.setCancelable(false);
                    del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.eliminar(getId());
                            lista = dao.verTodos();
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

                editar.setOnClickListener(view1 -> {
                    // aqui implementamos la edicion para agregar
                    int posi = Integer.parseInt(view.getTag().toString());
                    final Dialog dialog = new Dialog(a);
                    dialog.setTitle("Editar Mascota");
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.activity_editar_mascota);
                    dialog.show();

                    final EditText nom = (EditText) dialog.findViewById(R.id.nombre);
                    final Spinner ani = (Spinner) dialog.findViewById(R.id.animal);
                    final Spinner sex = (Spinner) dialog.findViewById(R.id.sexo);
                    final Spinner col = (Spinner) dialog.findViewById(R.id.color);
                    final Spinner col2 = (Spinner) dialog.findViewById(R.id.color2);
                    final Spinner ra = (Spinner) dialog.findViewById(R.id.raza);
                    final Spinner tam = (Spinner) dialog.findViewById(R.id.tamaño);
                    final EditText ed = (EditText) dialog.findViewById(R.id.edad);
                    final EditText desc = (EditText) dialog.findViewById(R.id.descripcion);

                    Button edit = (Button) dialog.findViewById(R.id.editar);
                    Button cancelar = (Button) dialog.findViewById(R.id.cancelar);

                    // eventos para botones
                    ma = lista.get(pos);
                    setId(ma.getId());

                    //recuperamos los datos que estan almacenados en la base de datos
                    nom.setText(ma.getNombre());
                    ani.setSelection(posicionSpinner(ani, ma.getAnimal()));
                    sex.setSelection(posicionSpinner(sex, ma.getSexo()));
                    col.setSelection(posicionSpinner(col, ma.getColor()));
                    col2.setSelection(posicionSpinner(col2, ma.getColor2()));
                    ra.setSelection(posicionSpinner(ra, ma.getRaza()));
                    tam.setSelection(posicionSpinner(tam, ma.getTamaño()));
                    ed.setText(ma.getEdad() + "");
                    desc.setText(ma.getDescripcion());

                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                ma = new Mascota(getId(),
                                        nom.getText().toString(),
                                        ani.getSelectedItem().toString(),
                                        sex.getSelectedItem().toString(),
                                        col.getSelectedItem().toString(),
                                        col2.getSelectedItem().toString(),
                                        ra.getSelectedItem().toString(),
                                        tam.getSelectedItem().toString(),
                                        Integer.parseInt(ed.getText().toString()),
                                        desc.getText().toString());
                                if(dao.editar(ma)){
                                    lista = dao.verTodos();
                                    Toast.makeText(a,"Editado", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    dialogo.dismiss();
                                }
                            } catch (Exception e) {
                                Toast.makeText(a,"Error no se guardo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                });
            }
        });
        */
        return v;
    }
}
