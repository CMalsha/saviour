package com.example.saviour;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class GMAPFragment extends SupportMapFragment implements OnMapReadyCallback {

    public GMAPFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Log.d("Google MAp", "Editing");
        // Define the bounds for Sri Lanka
        double south = 5.9199; // Southernmost latitude
        double west = 79.5222; // Westernmost longitude
        double north = 9.8354; // Northernmost latitude
        double east = 81.8797; // Easternmost longitude

        // Create a LatLngBounds object for Sri Lanka
        LatLngBounds sriLankaBounds = new LatLngBounds(new LatLng(south, west), new LatLng(north, east));

        // Move the camera to the Sri Lanka bounds
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(sriLankaBounds, 0);
        googleMap.moveCamera(cameraUpdate);

        googleMap.setMyLocationEnabled(true);

        BitmapDescriptor dotIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .icon(dotIcon)
                .anchor(0.5f, 0.5f));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .icon(dotIcon)
                .rotation(45f)
                .anchor(0.5f, 0.5f));

        googleMap.setOnMapClickListener(latLng -> {
            googleMap.addMarker(new MarkerOptions().position(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
        });
    }
}