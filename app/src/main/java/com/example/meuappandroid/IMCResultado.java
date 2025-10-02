package com.example.meuappandroid;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IMCResultado extends AppCompatActivity {

    TextView tvPeso, tvAltura, tvIMC, tvNome, tvResultado;
    ImageView imageView, perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imcresultado);
        tvPeso = findViewById(R.id.tvPeso);
        tvAltura = findViewById(R.id.tvAltura);
        tvIMC = findViewById(R.id.tvIMC);
        tvNome = findViewById(R.id.tvAltura2);
        imageView = findViewById(R.id.tvPerfil);
        perfil = findViewById(R.id.fotoPerfil);
        tvResultado = findViewById(R.id.tvAltura3);

        Bundle b = getIntent().getExtras();

        float peso = b.getFloat("peso");
        float altura = b.getFloat("altura");
        String nome = b.getString("nome");
        float imc = (peso)/(altura * altura);

        tvPeso.setText("Peso: " + Float.toString(peso) + "kg");
        tvAltura.setText("Altura: " + Float.toString(altura));
        tvIMC.setText("IMC: " + String.format(Float.toString(imc), "%.2f"));
        tvNome.setText("Nome: " + nome);
        perfil.setImageResource(R.drawable.patinho);

        if (imc < 18.5) {
            imageView.setImageResource(R.drawable.abaixopeso);
        } else if (imc < 24.9) {
            imageView.setImageResource(R.drawable.normal);
        } else if (imc < 29.9) {
            imageView.setImageResource(R.drawable.sobrepeso);
        } else if (imc < 34.9) {
            imageView.setImageResource(R.drawable.obesidade1);
        } else if (imc < 39.9) {
            imageView.setImageResource(R.drawable.obesidade2);
        } else {
            imageView.setImageResource(R.drawable.obesidade3);
        }

        if (imc < 20){
            tvResultado.setText(String.format("Você precisa ganhar: " + ((imc * (altura * altura)) - peso), "%.2f"));
        }
        else if (imc > 25) {
            tvResultado.setText(String.format("Você precisa perder: " + (peso - 24.9 * (altura * altura)), "%.2f"));
        }
        else {
            tvResultado.setText("Seu peso está bom!");
        }

    }
}