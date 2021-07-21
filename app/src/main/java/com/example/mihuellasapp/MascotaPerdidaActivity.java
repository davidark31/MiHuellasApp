package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Modelo.Mascota;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;


import java.util.Calendar;
import java.util.HashMap;


public class MascotaPerdidaActivity extends AppCompatActivity {

    private Mascota m;
    private ImageView perfil;
    private TextView nombre;
    private Button fecha, ubicacion, cancelar, publicar;
    private String fechaPerdido, longitud, latitud, descripcionSucesoString, recompensaString;
    private EditText descripcionSuceso, recompensa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_perdida);

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
        m.setContacto(MascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Contacto", "none"));


        perfil = findViewById(R.id.iv_perfil_mascota);
        nombre = findViewById(R.id.txt_nombre_mascota_perdida);
        fecha = findViewById(R.id.btn_fecha_perdido);
        ubicacion = findViewById(R.id.btn_ubicacion_mascota_perdida);
        cancelar = findViewById(R.id.btn_cancelar_perdido);
        publicar = findViewById(R.id.btn_cambiar_estado_perdido);
        descripcionSuceso = findViewById(R.id.txt_descripcionSuceso);
        recompensa = findViewById(R.id.txt_recompensa);

        Picasso.get().load(m.getImageUrl()).placeholder(R.mipmap.lost).into(perfil);
        nombre.setText(m.getNombre());

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

        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObtenerCoordendasActual();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        publicar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Mascotas").child(m.getId()).child("Estado").setValue("Perdido").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        descripcionSucesoString = descripcionSuceso.getText().toString();
                        recompensaString = recompensa.getText().toString();


                        if (fechaPerdido.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Ingrese Fecha", Toast.LENGTH_SHORT).show();

                        } else {
                            if (descripcionSucesoString.isEmpty()) {
                                descripcionSucesoString = " ";
                            }
                            if (recompensaString.isEmpty()) {
                                recompensaString = "Ninguna";
                            }
                            if (longitud.isEmpty() || latitud.isEmpty()) {
                                latitud = "-38.7333";
                                longitud = "-72.6667";
                            }


                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MascotasPerdidas");
                            String mpID = m.getId();

                            HashMap<String, Object> map = new HashMap<>();
                            map.put("Id", mpID);
                            map.put("IDMascota", m.getId());
                            map.put("Nombre", m.getNombre());
                            map.put("Animal", m.getAnimal());
                            map.put("Sexo", m.getSexo());
                            map.put("Raza", m.getRaza());
                            map.put("Color", m.getColor());
                            map.put("Color2", m.getColor2());
                            map.put("Tamaño", m.getTamaño());
                            map.put("Descripcion", m.getDescripcion());
                            map.put("Edad", m.getEdad());
                            map.put("IdDueño", m.getIdDueño());
                            map.put("ImageUrl", m.getImageUrl());
                            map.put("Estado", "Perdido");
                            map.put("Fecha", fechaPerdido);
                            map.put("Latitud", latitud);
                            map.put("Longitud", longitud);
                            map.put("DescripcionSuceso", descripcionSucesoString);
                            map.put("Recompensa", recompensaString);
                            map.put("Contacto",m.getContacto());


                            ref.child(mpID).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                        }

                        finish();
                        Toast.makeText(getApplicationContext(), "Se ha registrado como perdida", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        finish();
                        Toast.makeText(getApplicationContext(), "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    public void ObtenerCoordendasActual() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MascotaPerdidaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            getCoordenada();
        }
    }

    private void getCoordenada() {

        try {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(3000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    LocationServices.getFusedLocationProviderClient(MascotaPerdidaActivity.this).removeLocationUpdates(this);
                    if (locationResult != null && locationResult.getLocations().size() > 0) {
                        int latestLocationIndex = locationResult.getLocations().size() - 1;
                        latitud = locationResult.getLocations().get(latestLocationIndex).getLatitude() + "";
                        longitud = locationResult.getLocations().get(latestLocationIndex).getLongitude() + "";
                  Toast.makeText(getApplicationContext(),"localización obtenida",Toast.LENGTH_SHORT).show();
                    }
                }

            }, Looper.myLooper());

        } catch (Exception ex) {
            System.out.println("Error es :" + ex);
        }

    }


}