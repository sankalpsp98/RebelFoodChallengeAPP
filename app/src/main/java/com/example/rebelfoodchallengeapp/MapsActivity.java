package com.example.rebelfoodchallengeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView textView = findViewById(R.id.address);
        textView.setText(dataWire.getAddress());
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

        /**
         * map style
         */
        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.sankalpstyle);
        mMap.setMapStyle(mapStyleOptions);
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLng current = new LatLng(dataWire.getLat(), dataWire.getLog());

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(current)      // Sets the center of the map to Mountain View
                        .bearing(0)// Sets the orientation of the camera to east
                        //.tilt(90)// Sets the tilt of the camera to 30 degrees
                        .zoom(2000)                   // Sets the zoom
                        .build();// Creates a CameraPosition from the builder

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                mMap.addMarker(new MarkerOptions().position(current).title(dataWire.getLat()+" , "+dataWire.getLog()));
            }
        });



    }
}
