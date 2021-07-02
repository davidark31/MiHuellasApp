package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registro_AnimalActivity extends AppCompatActivity {

    private Spinner spAnimal,spTamano, spRaza, spColor,spSexo;
    private EditText etLugar;
    private RadioButton rbEdad;//rbCacho,rbAdul,rbAnci;
    private RadioGroup rgEdad;
    private Button btnGuardar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_animal);

        spAnimal = findViewById(R.id.sp_animal);

        spTamano = findViewById(R.id.sp_tamaño);
        spRaza = findViewById(R.id.sp_raza);
        spColor = findViewById(R.id.sp_color);
        spSexo = findViewById(R.id.sp_sexo);
        etLugar = findViewById(R.id.et_lugar);

        rgEdad = (RadioGroup) findViewById(R.id.rg_edad);

        //rbCacho = findViewById(R.id.rb_cachorro);
        //rbAdul = findViewById(R.id.rb_adulto);
        //rbAnci = findViewById(R.id.rb_anciano);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        btnGuardar = findViewById(R.id.btn_guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String animal =  spAnimal.getSelectedItem().toString();
                String tamano = spAnimal.getSelectedItem().toString();
                String raza = spRaza.getSelectedItem().toString();
                String color = spColor.getSelectedItem().toString();
                String sexo = spSexo.getSelectedItem().toString();
                String lugar = etLugar.getText().toString();
                
                int selectedId=rgEdad.getCheckedRadioButtonId();
                rbEdad=(RadioButton)findViewById(selectedId);
                String edad = rbEdad.getText().toString();


                if (lugar.isEmpty()) {
                    Toast.makeText(Registro_AnimalActivity.this, "Lugar es Obligatorio", Toast.LENGTH_SHORT).show();
                }else {
                    addDatatoFirebase(animal,tamano,raza,color,sexo,lugar,edad);
                }
            }
        });

    }



    private void addDatatoFirebase(String animal, String tamano, String raza,String color, String sexo, String lugar,String edad) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("animal", animal);
        map.put("tamaño", tamano);
        map.put("raza", raza);
        map.put("color", color);
        map.put("sexo", sexo);
        map.put("lugar", lugar);
        map.put("edad", edad);


        databaseReference.child("TablaDesdeLa app").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Creacion Exitosa", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cancelar(View view) {
        Intent next;
        next = new Intent(this, Inicio_RegistroActivity.class);
        startActivity(next);
        finish();
    }

}