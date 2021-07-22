package com.example.mihuellasapp.Fragments;

import android.content.Intent;
import android.content.pm.PackageInfo;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Adapter.AdaptadorMascotas;
import com.example.mihuellasapp.Adapter.AdaptadorMascotasPerdidas;
import com.example.mihuellasapp.Adapter.AdaptadorMisPublicaciones;
import com.example.mihuellasapp.Adapter.AdaptadorPublicaciones;
import com.example.mihuellasapp.MapsActivity;
import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.MascotaPerdida;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.example.mihuellasapp.PublicacionAnimalEncontrado;
import com.example.mihuellasapp.R;
import com.example.mihuellasapp.Registrar_MascotaActivity;
import com.example.mihuellasapp.databinding.ActivityPublicacionesAnimalesPerdidosBinding;
import com.example.mihuellasapp.publicacionesAnimalesPerdidosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BusquedaFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<MascotaPerdida> lMascotasPerdidas;
    private AdaptadorMascotasPerdidas mascotasPerdidasAdapter;
    private ImageView menu,animales;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lMascotasPerdidas = new ArrayList<>();
        buscarMascotasPerdidas();
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);
        animales = view.findViewById(R.id.menu_animalesPublicados);
        menu=view.findViewById(R.id.menu);


        recyclerView = view.findViewById(R.id.rv_mascotas_perdidas);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);



        mascotasPerdidasAdapter = new AdaptadorMascotasPerdidas(getContext(), lMascotasPerdidas);
        mascotasPerdidasAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mascotasPerdidasAdapter);


        animales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(getContext(), publicacionesAnimalesPerdidosActivity.class);
                startActivity(next);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mensaje();
            }
        });

        return view;
    }

    private void mensaje(){
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    +   "\"app_id\": \"d974c52c-d6b1-490b-afe6-0d6157f02877\","
                    +   "\"include_player_ids\": [\"3dfd4d96-bd1f-4bf5-ba23-fc8250e52ccf\"],"
                    +   "\"data\": {\"foo\": \"bar\"},"
                    +   "\"contents\": {\"en\": \"English Message\"}"
                    + "}";


            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

        } catch(Throwable t) {
            t.printStackTrace();
        }
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