package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Modelo.Mascota;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;


import java.util.Calendar;


public class MascotaPerdidaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Mascota m;
    private ImageView perfil;
    private TextView nombre;
    private Button fecha, ubicacion;
    private String fechaPerdido;
    private boolean permitido;
    private MapView mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_perdida);

        verificarPermiso();


        m = new Mascota();
        m.setId(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Id", "none"));
        m.setNombre(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Nombre", "none"));
        m.setRaza(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Raza", "none"));
        m.setAnimal(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Animal", "none"));
        m.setColor(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color", "none"));
        m.setColor2(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color2", "none"));
        m.setEdad(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Edad", "none"));
        m.setEstado(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Estado", "none"));
        m.setTamaño(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Tamaño", "none"));
        m.setDescripcion(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Descripcion", "none"));
        m.setImageUrl(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("ImageUrl", "none"));
        m.setIdDueño(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("IdDueño", "none"));
        m.setSexo(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Sexo", "none"));


        perfil = findViewById(R.id.iv_perfil_mascota);
        nombre = findViewById(R.id.txt_nombre_mascota_perdida);
        fecha = findViewById(R.id.btn_fecha_perdido);
        ubicacion = findViewById(R.id.btn_ubicacion_mascota_perdida);
        mapa = findViewById(R.id.mapView);

        Picasso.get().load(m.getImageUrl()).placeholder(R.mipmap.lost).into(perfil);
        nombre.setText(m.getNombre());

        if (permitido = true) {
            mapa.getMapAsync(this);
            mapa.onCreate(savedInstanceState);
        }


        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int año = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(MascotaPerdidaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fechaPerdido = dayOfMonth + "/" + month + "/" + year;
                    }
                }, año, mes, dia);
                dpd.show();
            }
        });

    }

    private void verificarPermiso() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(MascotaPerdidaActivity.this, "permiso concedido", Toast.LENGTH_SHORT).show();
                permitido = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mapa.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapa.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapa.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapa.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapa.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapa.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapa.onLowMemory();
    }
}