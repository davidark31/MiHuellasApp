package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registrar_MascotaActivity extends AppCompatActivity {

    private Spinner animal, sexo, raza, color, color2, tamaño;
    private RadioButton cachorro, adulto, anciano;
    private EditText descripcion, nombre;
    private ImageButton foto;
    private Button cancelar, registrar;
    private FirebaseAuth auth;
    private DatabaseReference base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);

        //asociar variables
        animal = (Spinner) findViewById(R.id.spn_animal);
        sexo = (Spinner) findViewById(R.id.spn_sexo);
        raza = (Spinner) findViewById(R.id.spn_raza);
        color = (Spinner) findViewById(R.id.spn_color);
        color2 = (Spinner) findViewById(R.id.spn_color2);
        tamaño = (Spinner) findViewById(R.id.spn_tamaño);
        foto = (ImageButton) findViewById(R.id.imb_foto);
        descripcion = (EditText) findViewById(R.id.txt_descripcion);
        nombre = (EditText) findViewById(R.id.txt_nombre_mascota);
        cancelar = (Button) findViewById(R.id.btn_cancelar);
        registrar = (Button) findViewById(R.id.btn_registrar_mascota);
        cachorro = (RadioButton) findViewById(R.id.rdb_cachorro);
        adulto = (RadioButton) findViewById(R.id.rdb_adulto);
        anciano = (RadioButton) findViewById(R.id.rdb_anciano);
        auth = FirebaseAuth.getInstance();
        base = FirebaseDatabase.getInstance().getReference();

        //Boton Registrar
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos las variables como String
                String nombreString = nombre.getText().toString();
                String animalString = animal.getSelectedItem().toString();
                String sexoString = sexo.getSelectedItem().toString();
                String razaString = raza.getSelectedItem().toString();
                String colorString = color.getSelectedItem().toString();
                String color2String = color2.getSelectedItem().toString();
                String tamañoString = tamaño.getSelectedItem().toString();
                String descipcionString = descripcion.getText().toString();
                String edad = "";

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
                if (nombreString.isEmpty() || edad.equals("")) {
                    Toast.makeText(getApplicationContext(), "Faltan Datos", Toast.LENGTH_SHORT).show();
                } else {
                    registrarMascota(nombreString, animalString, sexoString, razaString, colorString, color2String, tamañoString, descipcionString, edad);
                }

            }
        });


        //boton cancelar
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void registrarMascota(String nombre, String animal, String sexo, String raza, String color, String color2, String tamaño, String descipcion, String edad) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Nombre", nombre);
        map.put("Animal", animal);
        map.put("Sexo", sexo);
        map.put("Raza", raza);
        map.put("Color", color);
        map.put("Color2", color2);
        map.put("Tamaño", tamaño);
        map.put("Descripcion", descipcion);
        map.put("Edad", edad);
        map.put("IdDueño", auth.getCurrentUser().getUid());

        base.child("Mascotas").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    finish();
                    Toast.makeText(Registrar_MascotaActivity.this, "Mascota Registrada", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}