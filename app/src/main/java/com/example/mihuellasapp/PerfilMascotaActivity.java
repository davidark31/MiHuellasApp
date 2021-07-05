package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Modelo.Mascota;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PerfilMascotaActivity extends AppCompatActivity {


    private Mascota m;
    private Spinner animal, sexo, raza, color, color2, tamaño;
    private RadioButton cachorro, adulto, anciano;
    private EditText descripcion, nombre;
    private TextView estado;
    private ImageView foto;
    private Button cancelar, registrar;


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
        registrar = findViewById(R.id.btn_editar_mascota);
        cachorro = findViewById(R.id.rdb_cachorro);
        adulto = findViewById(R.id.rdb_adulto);
        anciano = findViewById(R.id.rdb_anciano);


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


        if (tamaño.equals("Cachorro")) {
            cachorro.setChecked(true);
        } else {
            if (tamaño.equals("adulto")) {
                adulto.setChecked(true);
            } else {
                if (tamaño.equals("anciano")) {
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





    /*    FirebaseDatabase.getInstance().getReference().child("Mascotas").child(m.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                m = dataSnapshot.getValue(Mascota.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });*/

        // nombre.setText(m.getNombre());
        // Toast.makeText(PerfilMascotaActivity.this, "nombre" + m.getNombre(), Toast.LENGTH_SHORT).show();

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
}