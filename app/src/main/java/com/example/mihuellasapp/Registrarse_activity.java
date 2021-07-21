package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registrarse_activity extends AppCompatActivity {

    private EditText  nombre, correo, contraseña, telefono;
    private TextView login;
    private Button registrar;
    private FirebaseAuth auth;
    private DatabaseReference mRootRef;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        nombre = (EditText) findViewById(R.id.txt_nombre_registro);
        correo = (EditText) findViewById(R.id.txt_correo_ingreso);
        contraseña = (EditText) findViewById(R.id.txt_contraseña_ingreso);
        registrar = (Button) findViewById(R.id.btn_ingresar_usuario);
        login = (TextView) findViewById(R.id.register_user);
        telefono = findViewById(R.id.txt_telefono_registro);

        auth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        mRootRef = FirebaseDatabase.getInstance().getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrarse_activity.this, LoginActivity.class));
                finish();
            }
        });

        //Boton Registrarse
        registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nombreString = nombre.getText().toString();
                String telefonoString = telefono.getText().toString();
                String correoString = correo.getText().toString();
                String contraseñaString = contraseña.getText().toString();
                if ( telefonoString.isEmpty() || nombreString.isEmpty() || correoString.isEmpty() || contraseñaString.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Faltan Datos", Toast.LENGTH_SHORT).show();
                } else {
                    if (contraseñaString.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Contraseña muy corta", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser( nombreString,telefonoString, correoString, contraseñaString);
                    }
                }
            }
        });

    }

    //Metodo Registro Usuario
    private void registerUser( String nombre,String tel, String correo, String contraseña) {
        pd.setMessage("Espere...");
        pd.show();
        auth.createUserWithEmailAndPassword(correo, contraseña).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("Nombre", nombre);
                map.put("Telefono", tel);
                map.put("Correo", correo);
                map.put("ID", auth.getCurrentUser().getUid());
                map.put("Contraseña", contraseña);

                mRootRef.child("Usuarios").child(auth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Toast.makeText(Registrarse_activity.this, "Creacion Exitosa", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Registrarse_activity.this, PrincipalActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}