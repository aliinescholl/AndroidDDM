package com.example.calcularimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText edPeso, edAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        edPeso = findViewById(R.id.editPeso);
        edAltura = findViewById(R.id.editAltura);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, IMCResultado.class);
            float peso = Float.parseFloat(edPeso.getText().toString());
            float altura = Float.parseFloat(edAltura.getText().toString());

            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);

            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}