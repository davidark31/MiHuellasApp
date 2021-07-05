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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mihuellasapp.Fragments.RegistroFragment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

public class Registro_AnimalActivity extends AppCompatActivity {

    private Spinner spAnimal,spTamaño, spRaza, spColor,spColor2, spSexo;
    private EditText txtDescripcion;
    private RadioButton rbCacho,rbAdul,rbAnci;
    private Button btnGuardar,btnCancelar;
    private ImageButton foto;
    private String edad;
    private Uri imageUri;
    private String imageUrl;

    private FirebaseAuth auth;
    private DatabaseReference base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_animal);

        foto = (ImageButton) findViewById(R.id.imb_Publicacion);
        spAnimal = (Spinner) findViewById(R.id.spn_tipo_animal);
        spTamaño = (Spinner) findViewById(R.id.spn_tamaño_animal);
        spRaza = (Spinner) findViewById(R.id.spn_raza_animal);
        spColor = (Spinner) findViewById(R.id.spn_color_animal);
        spColor2 = (Spinner) findViewById(R.id.spn_color2_animal);
        spSexo = (Spinner) findViewById(R.id.spn_sexo_animal);
        txtDescripcion = (EditText) findViewById(R.id.txt_descripcion_animal);
        rbCacho = (RadioButton) findViewById(R.id.rdb_cachorro_animal);
        rbAdul = (RadioButton) findViewById(R.id.rdb_adulto_animal);
        rbAnci = (RadioButton) findViewById(R.id.rdb_anciano_animal);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar_publicacion);

        auth = FirebaseAuth.getInstance();
        base = FirebaseDatabase.getInstance().getReference();
        btnGuardar = findViewById(R.id.btn_publicar_animal);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicarAnimal();
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().start(Registro_AnimalActivity.this);
            }
        });

        //boton cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void publicarAnimal(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Publicando");
        pd.show();

        String animal = spAnimal.getSelectedItem().toString();
        String tamaño = spTamaño.getSelectedItem().toString();
        String raza = spRaza.getSelectedItem().toString();
        String color = spColor.getSelectedItem().toString();
        String color2 = spColor2.getSelectedItem().toString();
        String sexo = spSexo.getSelectedItem().toString();
        String descripcion = txtDescripcion.getText().toString();
        edad = "";

        if (rbCacho.isChecked()) {
            edad = "Cachorro";
        } else {
            if (rbAdul.isChecked()) {
                edad = "adulto";
            } else {
                if (rbAnci.isChecked()) {
                    edad = "anciano";
                }
            }
        }
        if (descripcion.isEmpty() || edad.equals("")) {
            Toast.makeText(getApplicationContext(), "Descripcion es Obligatorio", Toast.LENGTH_SHORT).show();
        } else {


            if (imageUri != null) {
                final StorageReference filePath = FirebaseStorage.getInstance().getReference("FotoPublicacionEncontrados").child(System.currentTimeMillis() + "." + obtenerExtensionArchivo(imageUri));
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
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("PublicacionesAnimalesEncontrados");
                        String publicacionID = ref.push().getKey();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Id",publicacionID);
                        map.put("AnimalPublicacion", animal);
                        map.put("TamañoPublicacion", tamaño);
                        map.put("RazaPublicacion", raza);
                        map.put("ColorPublicacion", color);
                        map.put("SexoPublicacion", sexo);
                        map.put("Color2Publicacion", color2);
                        map.put("DescripcionPublicacion", descripcion);
                        map.put("EdadPublicacion", edad);
                        map.put("IdDueño", auth.getCurrentUser().getUid());
                        map.put("ImageUrl", imageUrl);


                        ref.child(publicacionID).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    finish();
                                    Toast.makeText(Registro_AnimalActivity.this, "Publicacion Exitosa ", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Registro_AnimalActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "No selecciono imagen!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private String obtenerExtensionArchivo(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            foto.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "Intentelo Nuevamente!", Toast.LENGTH_SHORT).show();

        }
    }

}