package com.example.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

public class SimplePaint extends View {

    public enum ShapeType {
        FREE_DRAW, LINE, RECTANGLE, CIRCLE
    }

    private ShapeType currentShape = ShapeType.FREE_DRAW;
    private Paint paint;
    private int currentColor = Color.BLACK;
    private float startX, startY;
    private Path currentPath;

    private final ArrayList<DrawnShape> shapes = new ArrayList<>();

    private static class DrawnShape {
        ShapeType type;
        Path path;
        float startX, startY, endX, endY;
        int color;

        DrawnShape(ShapeType type, Path path, float startX, float startY, float endX, float endY, int color) {
            this.type = type;
            this.path = path;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.color = color;
        }
    }

    public SimplePaint(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);
        paint.setAntiAlias(true);
    }

    public void setShapeType(ShapeType shapeType) {
        this.currentShape = shapeType;
    }

    public void setCurrentColor(int color) {
        this.currentColor = color;
    }

    public void clearCanvas() {
        shapes.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (DrawnShape shape : shapes) {

            paint.setColor(shape.color);
            switch (shape.type) {
                case FREE_DRAW:
                    canvas.drawPath(shape.path, paint);
                    break;
                case LINE:
                    canvas.drawLine(shape.startX, shape.startY, shape.endX, shape.endY, paint);
                    break;
                case RECTANGLE:
                    canvas.drawRect(shape.startX, shape.startY, shape.endX, shape.endY, paint);
                    break;
                case CIRCLE:
                    float radius = (float) Math.hypot(shape.endX - shape.startX, shape.endY - shape.startY);
                    canvas.drawCircle(shape.startX, shape.startY, radius, paint);
                    break;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;

                if (currentShape == ShapeType.FREE_DRAW) {
                    currentPath = new Path();
                    currentPath.moveTo(x, y);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (currentShape == ShapeType.FREE_DRAW && currentPath != null) {
                    currentPath.lineTo(x, y);
                    shapes.add(new DrawnShape(ShapeType.FREE_DRAW, new Path(currentPath),
                            0, 0, 0, 0, currentColor));
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                Path path = null;
                switch (currentShape) {
                    case FREE_DRAW:
                        break;
                    case LINE:
                        shapes.add(new DrawnShape(ShapeType.LINE, null, startX, startY, x, y, currentColor));
                        break;
                    case RECTANGLE:
                        shapes.add(new DrawnShape(ShapeType.RECTANGLE, null, startX, startY, x, y, currentColor));
                        break;
                    case CIRCLE:
                        shapes.add(new DrawnShape(ShapeType.CIRCLE, null, startX, startY, x, y, currentColor));
                        break;
                }
                invalidate();
                break;
        }

        return true;
    }
}