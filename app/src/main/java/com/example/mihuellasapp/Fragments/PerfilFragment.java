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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Adapter.AdaptadorMascotas;
import com.example.mihuellasapp.LoginActivity;
import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.PrincipalActivity;
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


public class PerfilFragment extends Fragment {

    private ImageButton logOut, nuevaMascota;
    private TextView nombre;
    private RecyclerView recyclerView;
    private List<Mascota> lMascotas;
    private AdaptadorMascotas mascotasAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lMascotas = new ArrayList<>();
        buscarMascotas();


        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_perfil);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        mascotasAdapter = new AdaptadorMascotas( getContext(), lMascotas);
        mascotasAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mascotasAdapter);

        nuevaMascota = view.findViewById(R.id.img_agregar);
        logOut = view.findViewById(R.id.logout);
        nombre = view.findViewById(R.id.tv_titulo);
        nombre.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());


        //Pantalla Nueva Mascota
        nuevaMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(getContext(), Registrar_MascotaActivity.class);
                startActivity(next);
            }
        });

        //Cerrar Sesion
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Saliendo", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
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