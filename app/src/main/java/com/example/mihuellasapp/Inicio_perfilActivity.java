package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mihuellasapp.Adapter.AdaptadorMascotas;
import com.example.mihuellasapp.Modelo.Mascota;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Inicio_perfilActivity extends AppCompatActivity {

    private ImageButton busqueda, perfil, subir, logOut, nuevaMascota;
    private TextView nombre;
    private RecyclerView recyclerView;
    private List<Mascota> lMascotas;
    private AdaptadorMascotas mascotasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_perfil);


        busqueda = (ImageButton) findViewById(R.id.ib_busqueda_perfil);
        perfil = (ImageButton) findViewById(R.id.ib_perfil_perfil);
        subir = (ImageButton) findViewById(R.id.ib_notificar_perfil);
        nuevaMascota = (ImageButton) findViewById(R.id.img_agregar);
        logOut = (ImageButton) findViewById(R.id.logout);
        nombre = (TextView) findViewById(R.id.tv_titulo);
        nombre.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        lMascotas = new ArrayList<>();
        buscarMascotas();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_mascotas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Inicio_perfilActivity.this));

        mascotasAdapter = new AdaptadorMascotas(lMascotas);
        recyclerView.setAdapter(mascotasAdapter);


        //Pantalla Mascotas Perdidas
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(Inicio_perfilActivity.this, Inicio_busquedaActivity.class);
                startActivity(next);
                finish();
            }
        });

        //Pantalla Nueva Mascota
        nuevaMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(Inicio_perfilActivity.this, Registrar_MascotaActivity.class);
                startActivity(next);
            }
        });

        //Cerrar Sesion
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Inicio_perfilActivity.this, "Saliendo", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Inicio_perfilActivity.this, LoginActivity.class));
                finish();
            }
        });

        //Pantalla Perfil usuario
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(Inicio_perfilActivity.this, Inicio_perfilActivity.class);
                startActivity(next);
                finish();
            }
        });

        //pantalla reportar mascota perdida
        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(Inicio_perfilActivity.this, Inicio_RegistroActivity.class);
                startActivity(next);
                finish();
            }
        });
    }

    public void buscarMascotas() {
        //prueba carga de mascotas
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Mascotas");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Mascota u = snap.getValue(Mascota.class);
                    if (u.getIdDueño().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                        lMascotas.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}