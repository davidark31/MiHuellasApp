package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mihuellasapp.Modelo.MascotaPerdida;
import com.example.mihuellasapp.Modelo.Publicacion;
import com.example.mihuellasapp.Modelo.Usuario;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Registrar_MascotaActivity extends AppCompatActivity {

    private Spinner animal, sexo, raza, color, color2, tamaño;
    private RadioButton cachorro, adulto, anciano;
    private EditText descripcion, nombre;
    private ImageButton foto;
    private Button cancelar, registrar;
    private FirebaseAuth auth;
    private DatabaseReference base;
    private Uri imageUri;
    private String imageUrl;
    private String edad, contacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);
        //asociar variables
        obtenerTelefonoUsuario(FirebaseAuth.getInstance().getUid());
        animal = (Spinner) findViewById(R.id.spn_animal);
        sexo = (Spinner) findViewById(R.id.spn_sexo_perfilMascota);
        raza = (Spinner) findViewById(R.id.spn_raza);
        color = (Spinner) findViewById(R.id.spn_color);
        color2 = (Spinner) findViewById(R.id.spn_color2);
        tamaño = (Spinner) findViewById(R.id.spn_tamaño);
        foto = (ImageButton) findViewById(R.id.imb_foto);
        descripcion = (EditText) findViewById(R.id.txt_descripcion);
        nombre = (EditText) findViewById(R.id.txt_nombre_mascota);
        cancelar = (Button) findViewById(R.id.btn_cancelar);
        registrar = (Button) findViewById(R.id.btn_editar_mascota);
        cachorro = (RadioButton) findViewById(R.id.rdb_cachorro);
        adulto = (RadioButton) findViewById(R.id.rdb_adulto);
        anciano = (RadioButton) findViewById(R.id.rdb_anciano);
        auth = FirebaseAuth.getInstance();
        base = FirebaseDatabase.getInstance().getReference();


        //Boton Registrar
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().start(Registrar_MascotaActivity.this);
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

    private void registrar() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Subiendo");
        pd.show();

        //Obtenemos las variables como String
        String nombreString = nombre.getText().toString();
        String animalString = animal.getSelectedItem().toString();
        String sexoString = sexo.getSelectedItem().toString();
        String razaString = raza.getSelectedItem().toString();
        String colorString = color.getSelectedItem().toString();
        String color2String = color2.getSelectedItem().toString();
        String tamañoString = tamaño.getSelectedItem().toString();
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
        if (nombreString.isEmpty() || edad.equals("")) {
            Toast.makeText(getApplicationContext(), "Faltan Datos", Toast.LENGTH_SHORT).show();
        } else {


            if (imageUri != null) {
                final StorageReference filePath = FirebaseStorage.getInstance().getReference("FotoPerfilMascota").child(System.currentTimeMillis() + "." + obtenerExtensionArchivo(imageUri));
                StorageTask uploadTaskt = filePath.putFile(imageUri);
                uploadTaskt.continueWithTask(new Continuation() {
                    @Override
                    public Object then(@NonNull Task task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri downlaoadUri = task.getResult();
                        imageUrl = downlaoadUri.toString();
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Mascotas");
                        String mascotaID = ref.push().getKey();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Id", mascotaID);
                        map.put("Nombre", nombreString);
                        map.put("Animal", animalString);
                        map.put("Sexo", sexoString);
                        map.put("Raza", razaString);
                        map.put("Color", colorString);
                        map.put("Color2", color2String);
                        map.put("Tamaño", tamañoString);
                        map.put("Descripcion", descipcionString);
                        map.put("Edad", edad);
                        map.put("IdDueño", auth.getCurrentUser().getUid());
                        map.put("ImageUrl", imageUrl);
                        map.put("Estado", "En casa");
                        map.put("Contacto", contacto);


                        ref.child(mascotaID).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                                finish();
                                Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registrar_MascotaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "No selecciono imagen!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void obtenerTelefonoUsuario(String s) {

        Query query = FirebaseDatabase.getInstance().getReference().child("Usuarios").orderByChild("ID").startAt(s).endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snap1 : snapshot.getChildren()) {
                    Usuario p = snap1.getValue(Usuario.class);
                    contacto=p.getTelefono();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Registrar_MascotaActivity.this, "error al cargar" + error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String obtenerExtensionArchivo(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            foto.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "Intentelo Nuevamente!", Toast.LENGTH_SHORT).show();

        }
    }
}