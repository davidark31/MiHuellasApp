package com.example.mihuellasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio_busqueda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_busqueda);
    }


    public void volverInicio(View view){
        Intent next;
        next=new Intent(this, Login.class);
        startActivity(next);
    }
    public void registrarMascotaPerdida(View view){
        Intent next;
        next=new Intent(this, Inicio_Registrar_mascota_perdida.class);
        startActivity(next);
    }
    public void inicioBusqueda(View view){
        Intent next;
        next=new Intent(this, Inicio_busqueda.class);
        startActivity(next);
    }

}