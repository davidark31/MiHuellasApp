package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mihuellasapp.Modelo.Publicacion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class EditarPublicacionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Publicacion p;
    private Spinner animal, sexo, raza, color, color2, tamano;
    private Double latidud, longitud;
    private RadioButton cachorro, adulto, anciano;
    private EditText descripcion;
    private ImageView foto;
    private Button cancelar, editar;
    private String edad;
    private Uri imageUri;
    private String imageUrl;
    private FirebaseAuth auth;
    private ImageButton eliminar;


    private MapView mapa;
    private boolean permitido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicacion);

        verificarPermiso();

        p = new Publicacion();
        p.setId(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Id", "none"));
        p.setRaza(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Raza", "none"));
        p.setAnimal(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Animal", "none"));
        p.setColor(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color", "none"));
        p.setColor2(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Color2", "none"));
        p.setEdad(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Edad", "none"));
        p.setTamano(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Tamano", "none"));
        p.setDescripcion(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Descripcion", "none"));
        p.setImageUrl(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("ImageUrl", "none"));
        p.setIdPublicador(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("IdDueno", "none"));
        p.setSexo(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Sexo", "none"));
        p.setFecha(EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Fecha", "none"));

        String latitudString = EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Latitud", "none");
        String longitudString = EditarPublicacionActivity.this.getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("Longitud","none");

        p.setLatitud(Double.parseDouble(latitudString));
        p.setLongitud(Double.parseDouble(longitudString));

        animal = findViewById(R.id.spn_animal);
        sexo = findViewById(R.id.spn_sexo_perfilMascota);
        raza = findViewById(R.id.spn_raza);
        color = findViewById(R.id.spn_color);
        color2 = findViewById(R.id.spn_color2);
        tamano = findViewById(R.id.spn_tamaño);
        foto = findViewById(R.id.img);
        descripcion = findViewById(R.id.txt_descripcion);
        cancelar = findViewById(R.id.btn_cancelar);
        editar = findViewById(R.id.btn_editar_mascota);
        cachorro = findViewById(R.id.rdb_cachorro);
        adulto = findViewById(R.id.rdb_adulto);
        anciano = findViewById(R.id.rdb_anciano);
        eliminar = findViewById(R.id.ib_eliminar);


        animal.setSelection(selectValue(animal, p.getAnimal()));
        sexo.setSelection(selectValue(sexo, p.getSexo()));
        color.setSelection(selectValue(color, p.getColor()));
        color2.setSelection(selectValue(color2, p.getColor2()));
        raza.setSelection(selectValue(raza, p.getRaza()));
        tamano.setSelection(selectValue(tamano, p.getTamano()));
        descripcion.setText(p.getDescripcion());
        Picasso.get().load(p.getImageUrl()).placeholder(R.mipmap.doggy).into(foto);


        mapa = findViewById(R.id.mapViewPublicacion);
        if (permitido = true) {
            mapa.getMapAsync(this);
            mapa.onCreate(savedInstanceState);
        }


        if (p.getEdad().equals("Cachorro")) {
            cachorro.setChecked(true);
            adulto.setChecked(false);
            anciano.setChecked(false);
        } else {
            if (p.getEdad().equals("adulto")) {
                cachorro.setChecked(false);
                adulto.setChecked(true);
                anciano.setChecked(false);
            } else {
                if (p.getEdad().equals("anciano")) {
                    cachorro.setChecked(false);
                    adulto.setChecked(false);
                    anciano.setChecked(true);
                }
            }
        }


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder del = new AlertDialog.Builder(EditarPublicacionActivity.this);
                del.setMessage("Estas seguro de eliminar la Publicación?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("PAniEncontrados").child(p.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Publicación Eliminada", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error al eliminar " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });

                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
                del.show();
            }
        });
    }

    private void actualizar() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Editando");
        pd.show();

        //Obtenemos las variables como String
        String animalString = animal.getSelectedItem().toString();
        String sexoString = sexo.getSelectedItem().toString();
        String razaString = raza.getSelectedItem().toString();
        String colorString = color.getSelectedItem().toString();
        String color2String = color2.getSelectedItem().toString();
        String tamañoString = tamano.getSelectedItem().toString();
        String descipcionString = descripcion.getText().toString();
        edad = "";

        //comprobamos la edad de la mascota
        if (cachorro.isChecked()) {
            edad = "Cachorro";
        } else {
            if (adulto.isChecked()) {
                edad = "adulto";
            } else {
                if (anciano.isChecked()) {
                    edad = "anciano";
                }
            }
        }
        if (edad.equals("")) {
            Toast.makeText(getApplicationContext(), "Faltan Datos", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("PAniEncontrados");

            HashMap<String, Object> map = new HashMap<>();
            map.put("Id", p.getId());
            map.put("Animal", animalString);
            map.put("Sexo", sexoString);
            map.put("Raza", razaString);
            map.put("Color", colorString);
            map.put("Color2", color2String);
            map.put("Tamano", tamañoString);
            map.put("Descripcion", descipcionString);
            map.put("Edad", edad);
            map.put("IdDueno", p.getIdPublicador());
            map.put("ImageUrl", p.getImageUrl());

            ref.child(p.getId()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        pd.dismiss();
                        finish();
                        Toast.makeText(getApplicationContext(), "Mascota Editada", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    finish();
                    Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


    private int selectValue(Spinner spinner, Object value) {
        int posicion = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    private String obtenerExtensionArchivo(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }


    private void verificarPermiso() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(EditarPublicacionActivity.this, "permiso concedido", Toast.LENGTH_SHORT).show();
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
        //googleMap.addMarker(new MarkerOptions().position(new LatLng(-38.7362611,-72.590546)).title("Lugar");
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Override, 14));

        LatLng temuco = new LatLng(p.getLatitud(), p.getLongitud());
        googleMap.addMarker(new MarkerOptions().position(temuco).title("Plaza Teodoro Schmidt"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temuco, 14));
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