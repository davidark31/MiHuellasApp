package com.example.mihuellasapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Adapter.AdaptadorMascotas;
import com.example.mihuellasapp.Adapter.AdaptadorMisPublicaciones;
import com.example.mihuellasapp.LoginActivity;
import com.example.mihuellasapp.MapsActivity;
import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.example.mihuellasapp.R;
import com.example.mihuellasapp.Registrar_MascotaActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BusquedaFragment extends Fragment {

    private ImageButton logOut, nuevaMascota;
    private TextView nombre;
    private RecyclerView recyclerView;
    private List<Mascota> lMascotas;
    private AdaptadorMascotas mascotasAdapter;
    private Button irMapa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lMascotas = new ArrayList<>();
        buscarMascotas();


        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_busqueda);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        irMapa = view.findViewById(R.id.botonMaps);

        irMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(getContext(),MapsActivity.class);
                startActivity(next);
            }
        });

        mascotasAdapter = new AdaptadorMascotas( getContext(), lMascotas);
        mascotasAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mascotasAdapter);

        return view;
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
                mascotasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {

        }
    }
}