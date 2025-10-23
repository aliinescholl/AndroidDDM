package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TinderButton extends androidx.appcompat.widget.AppCompatButton {

    float x0, y0;
    int colorR, colorG, colorB;

    public TinderButton(Context context) {
        super(context);
        this.setText("Tinder Button");
    }

    public TinderButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TinderButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setText("Tinder Button");
    }

    // onDraw: Recebe um canvas, que vai conter a região que vai ser desenhada. O que desenhar no canvas vai aparecer pro usuario
    //
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
    }

    // onTouch: chamada quando o componente for clicado
    // EventLoop
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            x0 = (int)x;
            y0 = (int)y;
        }

        int dx = (int)(x - x0);
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            colorR = Math.min(255, Math.max(0, 120 - dx / 5));
            colorG = Math.min(255, Math.max(0, 120 + dx / 5));
            colorB = 120;
        }

        if(event.getAction() == MotionEvent.ACTION_UP) {
            colorR = 120;
            colorG = 120;
            colorB = 120;
        }

        this.setBackgroundColor(Color.rgb(colorR, colorG, colorB));

        // Log Debug
        // Log.d("position", "X: " + x1 + " Y: " + " Event: " + event.getAction());

        return super.onTouchEvent(event);
    }
}
