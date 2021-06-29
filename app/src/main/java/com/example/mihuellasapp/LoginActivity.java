package com.example.mihuellasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btn_registrar, LoginButton;
    private Button btn_ingresar;
    private EditText correo, contraseña;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        contraseña = (EditText) findViewById(R.id.password);
        correo = (EditText) findViewById(R.id.correo);
        mAuth = FirebaseAuth.getInstance();

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Registrarse_activity.class));
                finish();
            }
        });

    }



}