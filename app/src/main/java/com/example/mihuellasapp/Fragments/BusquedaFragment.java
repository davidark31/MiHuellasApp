package com.example.mihuellasapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Adapter.AdaptadorMascotas;
import com.example.mihuellasapp.Adapter.AdaptadorMascotasPerdidas;
import com.example.mihuellasapp.MapsActivity;
import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.MascotaPerdida;
import com.example.mihuellasapp.R;
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
    private List<MascotaPerdida> lMascotasPerdidas;
    private AdaptadorMascotasPerdidas mascotasPerdidasAdapter;
    private Button irMapa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lMascotasPerdidas = new ArrayList<>();
        buscarMascotasPerdidas();


        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);
        recyclerView = view.findViewById(R.id.rv_mascotas_perdidas);
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
                next = new Intent(getContext(), MapsActivity.class);
                startActivity(next);
            }
        });

        mascotasPerdidasAdapter = new AdaptadorMascotasPerdidas(getContext(), lMascotasPerdidas);
        mascotasPerdidasAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mascotasPerdidasAdapter);

        return view;
    }


    public void buscarMascotasPerdidas() {
        //prueba carga de mascotas
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MascotasPerdidas");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    MascotaPerdida u = snap.getValue(MascotaPerdida.class);
                    lMascotasPerdidas.add(u);

                }
                mascotasPerdidasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }


}