package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity{

    Button btnA;
    Button btnB;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnA = findViewById(R.id.btnFA);
        btnB = findViewById(R.id.btnFB);

        btnA.setOnClickListener(v -> {
            fragment = new fragment_a();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // O replace remove todos os fragmentos atuais da view e adiciona o novo
            fragmentTransaction.replace(R.id.main, fragment);
            fragmentTransaction.commit();

        });
        btnB.setOnClickListener(v -> {
            fragment = new fragment_b();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // O replace remove todos os fragmentos atuais da view e adiciona o novo
            fragmentTransaction.replace(R.id.main, fragment);
            fragmentTransaction.commit();
        });

    }
}