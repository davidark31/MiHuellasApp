package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {

    private Button btn_ingresar;
    private EditText correo, contraseña;
    private FirebaseAuth mAuth;
    private TextView registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                      //  Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                        Log.d("mensaje", token);
                    }
                });

        btn_ingresar = (Button) findViewById(R.id.btn_ingresar_usuario);
        registrar = (TextView) findViewById(R.id.register_user);
        contraseña = (EditText) findViewById(R.id.txt_contraseña_ingreso);
        correo = (EditText) findViewById(R.id.txt_correo_ingreso);
        mAuth = FirebaseAuth.getInstance();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Registrarse_activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoString = correo.getText().toString();
                String contraseñaString = contraseña.getText().toString();
                if (contraseñaString.isEmpty() || contraseñaString.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                } else {
                    ingresarUsuario(correoString, contraseñaString);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this,PrincipalActivity.class));
            finish();
        }
    }

    private void ingresarUsuario(String correo, String contraseña) {
        mAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}