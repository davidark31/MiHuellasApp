package com.example.mihuellasapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Inicio_busquedaActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_busqueda);

        lv = (ListView)findViewById(R.id.lv_datos);

        /*
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                tv1.setText("la edad de "+ lv.getItemIdAtPosition(i) + " es " + edad[i]+ "años");
            }
        });
         */

        //FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //String userID = mAuth.getCurrentUser().getUid();

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.lista_publicacion,list);
        lv.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Publicaciones");
        //ref.child("Publicaciones").child(userID).addValueEventListener(new ValueEventListener() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Publicacion publi = snapshot.getValue(Publicacion.class);
                    String txt = publi.getLugar() + " : " + publi.getAnimal();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Fallo la lectura: " + databaseError.getCode());
            }
        });

    }


    public void volverInicio(View view){
     /*   Intent next;
        next=new Intent(this, Inicio_perfilActivity.class);
        startActivity(next);
        finish();*/
    }
    public void registrarMascotaPerdida(View view){
        Intent next;
        next=new Intent(this, Inicio_RegistroActivity.class);
        startActivity(next);
        finish();
    }
    public void inicioBusqueda(View view){
        Intent next;
        next=new Intent(this, Inicio_busquedaActivity.class);
        startActivity(next);
        finish();
    }

}