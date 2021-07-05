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

        p = new Publicacion();
        p.setIdPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Id", "none"));
        p.setRazaPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("RazaPublicacion", "none"));
        p.setAnimalPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("AnimalPublicacion", "none"));
        p.setColorPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("ColorPublicacion", "none"));
        p.setColor2Publicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color2Publicacion", "none"));
        p.setEdadPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("EdadPublicacion", "none"));
        p.setTamañoPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("TamañoPublicacion", "none"));
        p.setDescripcionPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("DescripcionPublicacion", "none"));
        p.setImageUrlPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("ImageUrl", "none"));
        p.setIdDueñoPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("IdDueño", "none"));
        p.setSexoPublicacion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("SexoPublicacion", "none"));
    }
}