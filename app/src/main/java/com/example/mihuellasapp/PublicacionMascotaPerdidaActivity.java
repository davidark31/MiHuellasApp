package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mihuellasapp.Modelo.Mascota;
import com.example.mihuellasapp.Modelo.MascotaPerdida;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

public class PublicacionMascotaPerdidaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private boolean permitido;
    private MapView mapa;
    private GoogleMap mMap;
    private ImageView foto;
    private MascotaPerdida m;
    private TextView nombre, animal, raza, sexo, color, color2, edad, tamaño, contacto,descripcion, suceso, fecha, recompensa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_mascota_perdida);
        // verificarPermiso();
        m = new MascotaPerdida();
        m.setId(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Id", "none"));
        m.setNombre(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Nombre", "none"));
        m.setRaza(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Raza", "none"));
        m.setAnimal(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Animal", "none"));
        m.setSexo(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Sexo", "none"));
        m.setColor(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color", "none"));
        m.setColor2(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color2", "none"));
        m.setEdad(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Edad", "none"));
        m.setEstado(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Estado", "none"));
        m.setTamaño(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Tamaño", "none"));
        m.setDescripcion(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Descripcion", "none"));
        m.setImageUrl(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("ImageUrl", "none"));
        m.setIdDueño(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("IdDueño", "none"));
        m.setiDMascota(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("IDMascota", "none"));
        m.setDescripcionSuceso(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("DescripcionSuceso", "none"));
        m.setFecha(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Fecha", "none"));
        m.setLatitud(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Latitud", "none"));
        m.setLongitud(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Longitud", "none"));
        m.setRecompensa(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Recompensa", "none"));
        m.setContacto(PublicacionMascotaPerdidaActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Contacto", "none"));


        foto=findViewById(R.id.imageView2);
        Picasso.get().load(m.getImageUrl()).placeholder(R.mipmap.perfil).into(foto);
        nombre = findViewById(R.id.txt_mascota_perdida_nombre);
        animal = findViewById(R.id.txt_mascota_perdida_animal);
        raza = findViewById(R.id.txt_mascota_perdida_raza);
        color = findViewById(R.id.txt_mascota_perdida_color);
        color2 = findViewById(R.id.txt_mascota_perdida_color2);
        edad = findViewById(R.id.txt_mascota_perdida_edad);
        tamaño = findViewById(R.id.txt_mascota_perdida_tamaño);
        sexo = findViewById(R.id.txt_mascota_perdida_sexo);
        descripcion = findViewById(R.id.txt_mascota_perdida_descripcion);
        suceso = findViewById(R.id.txt_mascota_perdida_suceso);
        recompensa = findViewById(R.id.txt_mascota_perdida_recompensa);
        fecha = findViewById(R.id.txt_mascota_perdida_fecha);
        contacto=findViewById(R.id.txt_mascota_perdida_contacto);


        nombre.setText(m.getNombre());
        animal.setText(m.getAnimal());
        raza.setText(m.getRaza());
        color.setText(m.getColor());
        color2.setText(m.getColor2());
        edad.setText(m.getEdad());
        tamaño.setText(m.getTamaño());
        sexo.setText(m.getSexo());
        descripcion.setText(m.getDescripcion());
        suceso.setText(m.getDescripcionSuceso());
        recompensa.setText(m.getRecompensa());
        fecha.setText(m.getFecha());
        contacto.setText(m.getContacto());


        mapa = findViewById(R.id.mv_ubicacion_perdida);
        if (permitido = true) {
            mapa.getMapAsync(this);
            mapa.onCreate(savedInstanceState);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        double lat=Double.parseDouble(m.getLatitud());
        double lon=Double.parseDouble(m.getLongitud());
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng temuco = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(temuco).title("Aqui se perdío"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temuco, 16));

    }

    private void verificarPermiso() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(PublicacionMascotaPerdidaActivity.this, "permiso concedido", Toast.LENGTH_SHORT).show();
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