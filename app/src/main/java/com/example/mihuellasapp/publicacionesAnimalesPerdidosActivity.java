package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mihuellasapp.Adapter.AdaptadorPublicaciones;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class publicacionesAnimalesPerdidosActivity extends AppCompatActivity {

    private AdaptadorPublicaciones publicacionesAdapter;
    private RecyclerView  recyclerView2;
    private List<Publicacion> lPublicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones_animales_perdidos);
        lPublicaciones = new ArrayList<>();
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

    public void buscarPublicaciones() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("PAniEncontrados");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snap1 : snapshot.getChildren()) {
                    Publicacion p = snap1.getValue(Publicacion.class);
                    lPublicaciones.add(p);

                }
                publicacionesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(publicacionesAnimalesPerdidosActivity.this, "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}