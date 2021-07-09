package com.example.mihuellasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.Publicacion;

public class EditarPublicacionActivity extends AppCompatActivity {

    private Publicacion p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicacion);


    }
}