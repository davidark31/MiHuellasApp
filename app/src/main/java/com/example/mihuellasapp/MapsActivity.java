package com.example.mihuellasapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.mihuellasapp.Modelo.Publicacion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mihuellasapp.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private DatabaseReference mDatabase;
    //private ArrayList<Marker> marcadores= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
  @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mDatabase.child("PAniEncontrados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                LatLng temuco = new LatLng(-38.7362611, -72.590546);
                mMap.addMarker(new MarkerOptions().position(temuco).title("Plaza Teodoro Schmidt"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temuco, 14));

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){
                    Publicacion p = snapshot1.getValue(Publicacion.class);
                   // Float latitud = p.getLatitud();
                   // Float longitud = p.getLongitud();
                    MarkerOptions markerOptions = new MarkerOptions();
                   // markerOptions.position(new LatLng(latitud,longitud));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

  /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng temuco = new LatLng(-38.7362611, -72.590546);
        mMap.addMarker(new MarkerOptions().position(temuco).title("Plaza Teodoro Schmidt"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temuco, 14));

        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
*/




}