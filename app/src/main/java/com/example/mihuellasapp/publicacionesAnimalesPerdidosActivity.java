package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mihuellasapp.Adapter.AdaptadorPublicaciones;
import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.MascotaPerdida;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class publicacionesAnimalesPerdidosActivity extends AppCompatActivity {

    private AdaptadorPublicaciones publicacionesAdapter;
    private RecyclerView recyclerView2;
    private List<Publicacion> lPublicaciones;
    private List<MascotaPerdida> mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones_animales_perdidos);
        lPublicaciones = new ArrayList<>();
        mascota = new ArrayList<>();
        mascotasPerdidas(FirebaseAuth.getInstance().getUid());
        buscarPublicaciones();

        recyclerView2 = findViewById(R.id.rv_publicaciones_usuarios);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(publicacionesAnimalesPerdidosActivity.this);
        linearLayoutManager2.setStackFromEnd(true);
        linearLayoutManager2.setReverseLayout(true);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        publicacionesAdapter = new AdaptadorPublicaciones(getApplicationContext(), lPublicaciones);
        publicacionesAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(publicacionesAdapter);


    }

    public void mascotasPerdidas(String s) {
        Query query = FirebaseDatabase.getInstance().getReference().child("MascotasPerdidas").orderByChild("IdDueño").startAt(s).endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snap1 : snapshot.getChildren()) {
                    MascotaPerdida p = snap1.getValue(MascotaPerdida.class);
                    mascota.add(p);
                }
                publicacionesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(publicacionesAnimalesPerdidosActivity.this, "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void buscarPublicaciones() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("PAniEncontrados");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot snap1 : snapshot.getChildren()) {
                    Publicacion p = snap1.getValue(Publicacion.class);
                    for (int i = 0; i < mascota.size(); i++) {
                        MascotaPerdida mas = mascota.get(i);
                        p.setSimilutid(similudes(mas, p));
                    }
                    lPublicaciones.add(p);
                }
                Collections.sort(lPublicaciones);
               publicacionesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(publicacionesAnimalesPerdidosActivity.this, "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int similudes(MascotaPerdida m, Publicacion p) {
        int cont = 0;
        if (m.getAnimal().equals(p.getAnimal())) {
            cont++;
        }
        if (m.getColor().equals(p.getColor())) {
            cont++;
        }
        if (m.getColor2().equals(p.getColor2())) {
            cont++;
        }
        if (m.getRaza().equals(p.getRaza())) {
            cont++;
        }
        if (m.getTamaño().equals(p.getTamano())) {
            cont++;
        }
        if (m.getEdad().equals(p.getEdad())) {
            cont++;
        }
        if (m.getSexo().equals(p.getSexo())) {
            cont++;
        }
        return cont;
    }

}