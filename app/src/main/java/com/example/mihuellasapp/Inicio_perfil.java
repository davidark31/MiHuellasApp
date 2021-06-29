package com.example.mihuellasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Inicio_perfil extends AppCompatActivity {

    private ImageButton busqueda, perfil, subir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_perfil);

        busqueda = (ImageButton) findViewById(R.id.ib_home_perfil);
        perfil = (ImageButton) findViewById(R.id.ib_corazon_perfil);
        subir = (ImageButton) findViewById(R.id.ib_registro_perfil);


        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(Inicio_perfil.this, Inicio_busqueda.class);
                startActivity(next);
            }
        });


    }

    public void volverInicio(View view) {
        Intent next;
        next = new Intent(this, Login.class);
        startActivity(next);
    }


    public void registrarMascotaPerdida(View view) {
        Intent next;
        next = new Intent(this, Inicio_Registrar_mascota_perdida.class);
        startActivity(next);
    }

    public void inicioBusqueda(View view) {

    }

}