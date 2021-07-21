package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mihuellasapp.Modelo.MascotaPerdida;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class AnimalEncontradoActivity extends AppCompatActivity implements OnMapReadyCallback {


    private Publicacion m;
    private boolean permitido;
    private MapView mapa;
    private GoogleMap mMap;
    private ImageView foto;
    private TextView nombre, animal, raza, sexo, color, color2, edad, tamaño, contacto,descripcion, suceso, fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_encontrado);

        m = new Publicacion();
        m.setId(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Id", "none"));
        m.setAnimal(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Animal", "none"));
        m.setRaza(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Raza", "none"));
        m.setAnimal(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Animal", "none"));
        m.setSexo(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Sexo", "none"));
        m.setColor(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color", "none"));
        m.setColor2(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color2", "none"));
        m.setEdad(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Edad", "none"));
        m.setTamano(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Tamaño", "none"));
        m.setDescripcion(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Descripcion", "none"));
        m.setImageUrl(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("ImageUrl", "none"));
        m.setIdPublicador(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("IdPublicador", "none"));
        m.setFecha(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Fecha", "none"));
        m.setLatitud(Double.parseDouble(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Latitud", "none")));
        m.setLongitud(Double.parseDouble(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Longitud", "none")));
        m.setContacto(AnimalEncontradoActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Contacto", "none"));




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
        fecha = findViewById(R.id.txt_mascota_perdida_fecha);
        contacto=findViewById(R.id.txt_mascota_perdida_contacto);


        animal.setText(m.getAnimal());
        raza.setText(m.getRaza());
        color.setText(m.getColor());
        color2.setText(m.getColor2());
        edad.setText(m.getEdad());
        tamaño.setText(m.getTamano());
        sexo.setText(m.getSexo());
        descripcion.setText(m.getDescripcion());
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
        double lat=(m.getLatitud());
        double lon=(m.getLongitud());
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng temuco = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(temuco).title("Aqui se encontró"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temuco, 16));

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