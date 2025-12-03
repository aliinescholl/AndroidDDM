package com.example.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private boolean isUserInteracting = false;
    private boolean seguirCoordenadas = true;
    TextView tvLatitude, tvLongitude, tvStatus;
    LocationManager locationManager;
    private MapView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_main);
        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvStatus = findViewById(R.id.tvStatus);
        Button button= findViewById(R.id.button);
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        map.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                isUserInteracting = true;
                tvStatus.setText("Interagindo com o mapa");
            }
            return false;
        });


        tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvStatus.setText("Seguindo Localização");
                seguirCoordenadas = true;
                isUserInteracting=false;
            }
        });
        button.setOnClickListener(v -> {
            getLocalizacao();
        });
        locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @SuppressLint("MissingPermission")
    private void getLocalizacao() {
        if (checkAndGetPermissions()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        } else {
            tvStatus.setText("Permissão Negada");
        }
    }

    public boolean checkAndGetPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
            return false;
        } else {
            return true;
        }

    }

    private void showUserLocation(double latitude, double longitude) {
        GeoPoint userLocation = new GeoPoint(latitude, longitude);
        if (seguirCoordenadas && !isUserInteracting) {
            map.getController().setCenter(userLocation);
            map.getController().setZoom(18.0);
            if (!isUserInteracting) {
                map.getController().animateTo(userLocation);
            }
        }
        Marker marker = new Marker(map);
        marker.setPosition(userLocation);
        marker.setTitle("Você está aqui");

        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().clear();
        map.getOverlays().add(marker);

        map.invalidate();
    }
    public final android.location.LocationListener locationListener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            DecimalFormat decimalFormat = new DecimalFormat("##.######");
            tvLatitude.setText("Latitude: " + decimalFormat.format(location.getLatitude()));
            tvLongitude.setText("Longitude: " + decimalFormat.format(location.getLongitude()));
            showUserLocation(location.getLatitude(), location.getLongitude());
        }
    };
}