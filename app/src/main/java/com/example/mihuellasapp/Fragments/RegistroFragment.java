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
import com.example.mihuellasapp.Adapter.AdaptadorMisPublicaciones;
import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.example.mihuellasapp.R;
import com.example.mihuellasapp.Registrar_MascotaActivity;
import com.example.mihuellasapp.Registro_AnimalActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RegistroFragment extends Fragment {

    private Button btnpublicar;
    private RecyclerView recyclerView;
    private List<Publicacion> lMisPublicaciones;
    private AdaptadorMisPublicaciones misPublicacionesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lMisPublicaciones = new ArrayList<>();
        buscarMisPublicaciones();


        //Publicacion p = new Publicacion("-MdyoPe96p-JWf_fKu_A","Gato","Chico","Quiltro","Amarillo","Amarillo","Macho","1registro","adulto","uBhrTvSCloetSswMo4TuDdlewa83","https://firebasestorage.googleapis.com/v0/b/regal-spark-317815.appspot.com/o/FotoPublicacionEncontrados%2F1625625303162.null?alt=media&token=d7695df5-4821-4fdc-9b9d-af8e3067ed2e","-122.084","37.4219983");
        //lMisPublicaciones.add(p);


        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_mis_publi);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        misPublicacionesAdapter = new AdaptadorMisPublicaciones( getContext(), lMisPublicaciones);
        misPublicacionesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(misPublicacionesAdapter);

        btnpublicar = (Button) view.findViewById(R.id.btn_publicar);
        btnpublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(getContext(), Registro_AnimalActivity.class);
                startActivity(next);
            }
        });

        return view;
    }



    public void buscarMisPublicaciones() {
        //prueba carga
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("PAniEncontrados");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snap1 : snapshot.getChildren()) {
                    Publicacion u = snap1.getValue(Publicacion.class);
                    if (u.getIdDueno().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                        lMisPublicaciones.add(u);

                }
                misPublicacionesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}


