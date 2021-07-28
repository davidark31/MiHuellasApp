package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Modelo.Mascota;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class PerfilMascotaActivity extends AppCompatActivity {


    private Mascota m;
    private Spinner animal, sexo, raza, color, color2, tamaño;
    private RadioButton cachorro, adulto, anciano;
    private EditText descripcion, nombre;
    private TextView estado;
    private ImageView foto, estadoCasa;
    private Button cancelar, editar;
    private String edad;
    private Uri imageUri;
    private String imageUrl;
    private FirebaseAuth auth;
    private ImageButton eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_mascota);
        m = new Mascota();
        m.setId(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Id", "none"));
        m.setNombre(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Nombre", "none"));
        m.setRaza(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Raza", "none"));
        m.setAnimal(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Animal", "none"));
        m.setColor(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color", "none"));
        m.setColor2(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color2", "none"));
        m.setEdad(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Edad", "none"));
        m.setEstado(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Estado", "none"));
        m.setTamaño(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Tamaño", "none"));
        m.setDescripcion(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Descripcion", "none"));
        m.setImageUrl(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("ImageUrl", "none"));
        m.setIdDueño(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("IdDueño", "none"));
        m.setSexo(PerfilMascotaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Sexo", "none"));

        animal = findViewById(R.id.spn_animal);
        sexo = findViewById(R.id.spn_sexo_perfilMascota);
        raza = findViewById(R.id.spn_raza);
        color = findViewById(R.id.spn_color);
        color2 = findViewById(R.id.spn_color2);
        tamaño = findViewById(R.id.spn_tamaño);
        estado = findViewById(R.id.tv_nombre);
        foto = findViewById(R.id.img);
        descripcion = findViewById(R.id.txt_descripcion);
        nombre = findViewById(R.id.txt_nombre_mascota);
        cancelar = findViewById(R.id.btn_cancelar);
        editar = findViewById(R.id.btn_editar_mascota);
        cachorro = findViewById(R.id.rdb_cachorro);
        adulto = findViewById(R.id.rdb_adulto);
        anciano = findViewById(R.id.rdb_anciano);
        eliminar = findViewById(R.id.ib_eliminar);
        estadoCasa = findViewById(R.id.iv_estado_casa);


        nombre.setText(m.getNombre());
        animal.setSelection(selectValue(animal, m.getAnimal()));
        sexo.setSelection(selectValue(sexo, m.getSexo()));
        color.setSelection(selectValue(color, m.getColor()));
        color2.setSelection(selectValue(color2, m.getColor2()));
        raza.setSelection(selectValue(raza, m.getRaza()));
        tamaño.setSelection(selectValue(tamaño, m.getTamaño()));
        descripcion.setText(m.getDescripcion());
        estado.setText(m.getEstado());
        Picasso.get().load(m.getImageUrl()).placeholder(R.mipmap.doggy).into(foto);


        if (m.getEstado().equals("En casa")) {
           estado.setTextColor(Color.parseColor("#3CAD4C"));

        } else {
            if (m.getEstado().equals("Perdido")) {
               estado.setTextColor(Color.parseColor("#E83030"));
            }

        }

        if (m.getEstado().equals("En casa")) {
            estadoCasa.setVisibility(View.GONE);
        }


        if (m.getEdad().equals("Cachorro")) {
            cachorro.setChecked(true);
            adulto.setChecked(false);
            anciano.setChecked(false);
        } else {
            if (m.getEdad().equals("adulto")) {
                cachorro.setChecked(false);
                adulto.setChecked(true);
                anciano.setChecked(false);
            } else {
                if (m.getEdad().equals("anciano")) {
                    cachorro.setChecked(false);
                    adulto.setChecked(false);
                    anciano.setChecked(true);
                }
            }
        }

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        estadoCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder del = new AlertDialog.Builder(PerfilMascotaActivity.this);
                del.setMessage("Cambiar estado a En casa?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("Mascotas").child(m.getId()).child("Estado").setValue("En casa").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Se ha cambiado el estado", Toast.LENGTH_SHORT).show();
                                FirebaseDatabase.getInstance().getReference().child("MascotasPerdidas").child(m.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Se ha cambiado el estado", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error al eliminar " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                              }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error al eliminar " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder del = new AlertDialog.Builder(PerfilMascotaActivity.this);
                del.setMessage("Estas seguro de eliminar la Mascota?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("Mascotas").child(m.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Mascota Eliminada", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error al eliminar " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
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

    private void actualizar() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Editando");
        pd.show();

        //Obtenemos las variables como String
        String nombreString = nombre.getText().toString();
        String animalString = animal.getSelectedItem().toString();
        String sexoString = sexo.getSelectedItem().toString();
        String razaString = raza.getSelectedItem().toString();
        String colorString = color.getSelectedItem().toString();
        String color2String = color2.getSelectedItem().toString();
        String tamañoString = tamaño.getSelectedItem().toString();
        String descipcionString = descripcion.getText().toString();
        edad = "";

        //comprobamos la edad de la mascota
        if (cachorro.isChecked()) {
            edad = "Cachorro";
        } else {
            if (adulto.isChecked()) {
                edad = "adulto";
            } else {
                if (anciano.isChecked()) {
                    edad = "anciano";
                }
            }
        }
        if (nombreString.isEmpty() || edad.equals("")) {
            Toast.makeText(getApplicationContext(), "Faltan Datos", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Mascotas");

            HashMap<String, Object> map = new HashMap<>();
            map.put("Id", m.getId());
            map.put("Nombre", nombreString);
            map.put("Animal", animalString);
            map.put("Sexo", sexoString);
            map.put("Raza", razaString);
            map.put("Color", colorString);
            map.put("Color2", color2String);
            map.put("Tamaño", tamañoString);
            map.put("Descripcion", descipcionString);
            map.put("Edad", edad);
            map.put("IdDueño", m.getIdDueño());
            map.put("ImageUrl", m.getImageUrl());
            map.put("Estado", m.getEstado());

            ref.child(m.getId()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        pd.dismiss();
                        finish();
                        Toast.makeText(getApplicationContext(), "Mascota Editada", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    finish();
                    Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
     /*       ref.child(m.getId()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        finish();
                        Toast.makeText(PerfilMascotaActivity.this, "Mascota Editada", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });*/

        }

    }


    private int selectValue(Spinner spinner, Object value) {
        int posicion = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    private String obtenerExtensionArchivo(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }
}