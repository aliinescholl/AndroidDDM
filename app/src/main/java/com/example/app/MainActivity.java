package com.example.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    PlanetAdapter adapter;
    List<Planet> planetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        planetas = new ArrayList<>();
        planetas.add(new Planet("Netuno", R.drawable.neptune));
        planetas.add(new Planet("Urano", R.drawable.uranus));
        planetas.add(new Planet("Saturno", R.drawable.saturn));
        planetas.add(new Planet("Venus", R.drawable.venus));
        planetas.add(new Planet("Mercurio", R.drawable.mercury));
        planetas.add(new Planet("Jupiter", R.drawable.jupter));
        planetas.add(new Planet("Marte", R.drawable.mars));
        planetas.add(new Planet("Terra", R.drawable.earth));

        adapter = new PlanetAdapter(this, R.layout.planet_item, planetas);

        listView.setAdapter(adapter);
    }
}