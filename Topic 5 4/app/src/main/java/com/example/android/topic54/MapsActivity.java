package com.example.android.topic54;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final double CIRCLE_RADIUS = 10_000;
    private static final double SCALE_METERS_TO_DEGREES = 1D /27 / 1000;
    private static final float DEFAULT_ZOOM = 10;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private GoogleMap mMap;
    private boolean isMarkersDrawn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                initOnMyLocationButton();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    private void initOnMyLocationButton() {
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(()-> {
            Location location = mMap.getMyLocation();
            if (location != null && !isMarkersDrawn) {
                moveCameraToMyLocation(location);
                drawMarkers(mMap.getMyLocation());
                isMarkersDrawn = true;
            }
            return true;
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initOnMyLocationButton();
            } else {
                Toast.makeText(this, "Разрешите приложению доступ к местоположению", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void moveCameraToMyLocation(@NonNull Location location) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.radius(CIRCLE_RADIUS);
        circleOptions.center(new LatLng(location.getLatitude(), location.getLongitude()));
        mMap.addCircle(circleOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(circleOptions.getCenter(), DEFAULT_ZOOM));
    }


    private void drawMarkers(@NonNull Location location) {
        List<LatLng> markers = generateListOfMarkers(location);
        for (LatLng marker : markers) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(marker.latitude, marker.longitude)));
        }
    }


    private List<LatLng> generateListOfMarkers(Location location) {
        int numberOfMarkers = 5;
        float[] distance = new float[1];
        List<LatLng> mList = new ArrayList<>();
        while (mList.size() < numberOfMarkers) {
            double randLatitude = (2*Math.random() - 1) * CIRCLE_RADIUS * SCALE_METERS_TO_DEGREES;
            double randLongitude = (2*Math.random() - 1) * CIRCLE_RADIUS * SCALE_METERS_TO_DEGREES;
            LatLng latLng = new LatLng(location.getLatitude() + randLatitude,
                    location.getLongitude() + randLongitude);
            Location.distanceBetween(location.getLatitude(), location.getLongitude(), latLng.latitude, latLng.longitude, distance);
            if (distance[0] < CIRCLE_RADIUS) {
                mList.add(latLng);
            }
        }
        return mList;
    }
}