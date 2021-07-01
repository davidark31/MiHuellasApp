package com.example.mihuellasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Inicio_perfilActivity extends AppCompatActivity {

    private ImageButton busqueda, perfil, subir, logOut,nuevaMascota;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private TextView nombre;


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
                next = new Intent(Inicio_perfilActivity.this, Inicio_Registrar_mascota_perdidaActivity.class);
                startActivity(next);
                finish();
            }
        });
    }

    public void volverInicio(View view) {
        Intent next;
        next = new Intent(this, Inicio_perfilActivity.class);
        startActivity(next);
        finish();
    }


    public void registrarMascotaPerdida(View view) {
        Intent next;
        next = new Intent(this, Inicio_Registrar_mascota_perdidaActivity.class);
        startActivity(next);
        finish();
    }

    public void inicioBusqueda(View view) {

    }

}