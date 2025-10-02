package com.example.meuappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText edPeso, edAltura, edNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.edBotao);
        edPeso = findViewById(R.id.tvPeso);
        edAltura = findViewById(R.id.edAltura);
        edNome = findViewById(R.id.edNome);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, IMCResultado.class);
            float peso = Float.parseFloat(edPeso.getText().toString());
            float altura = Float.parseFloat(edAltura.getText().toString());
            String nome = edNome.getText().toString();

            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);
            intent.putExtra("nome", nome);

            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}