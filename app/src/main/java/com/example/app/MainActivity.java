package com.example.app;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
public class MainActivity extends AppCompatActivity {
    SimplePaint simplePaint;
    Button btnFreeDraw, btnLine, btnRect, btnCircle, btnColor, btnClear;
    private int defaultColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePaint = findViewById(R.id.simplePaint);

        btnFreeDraw = findViewById(R.id.btnFreeDraw);
        btnLine = findViewById(R.id.btnLine);
        btnRect = findViewById(R.id.btnRect);
        btnCircle = findViewById(R.id.btnCircle);
        btnColor = findViewById(R.id.btnColor);
        btnClear = findViewById(R.id.btnClear);

        btnFreeDraw.setOnClickListener(v -> {
            simplePaint.setShapeType(SimplePaint.ShapeType.FREE_DRAW);
            Toast.makeText(this, "Modo: Traço Livre", Toast.LENGTH_SHORT).show();
        });

        btnLine.setOnClickListener(v -> {
            simplePaint.setShapeType(SimplePaint.ShapeType.LINE);
            Toast.makeText(this, "Modo: Linha", Toast.LENGTH_SHORT).show();
        });

        btnRect.setOnClickListener(v -> {
            simplePaint.setShapeType(SimplePaint.ShapeType.RECTANGLE);
            Toast.makeText(this, "Modo: Retângulo", Toast.LENGTH_SHORT).show();
        });

        btnCircle.setOnClickListener(v -> {
            simplePaint.setShapeType(SimplePaint.ShapeType.CIRCLE);
            Toast.makeText(this, "Modo: Círculo", Toast.LENGTH_SHORT).show();
        });

        btnColor.setOnClickListener(v -> {
            new ColorPickerDialog.Builder(this)
                    .setTitle("Escolha uma cor")
                    .setPreferenceName("ColorPickerDialog")
                    .setPositiveButton("Selecionar", new ColorEnvelopeListener() {
                        @Override
                        public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                            defaultColor = envelope.getColor();
                            simplePaint.setCurrentColor(defaultColor);
                            Toast.makeText(MainActivity.this, "Cor alterada!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.dismiss())
                    .attachAlphaSlideBar(true)
                    .attachBrightnessSlideBar(true)
                    .show();
        });

        btnClear.setOnClickListener(v -> simplePaint.clearCanvas());
    }
}