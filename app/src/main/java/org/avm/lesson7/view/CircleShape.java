package org.avm.lesson7.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Random;

public class CircleShape extends Shape {
    private float radius;
    private float cx;
    private float cy;

    public CircleShape(Context context) {
        super(context);
    }

    public CircleShape(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    void init(@Nullable AttributeSet set) {
        super.init(set);
        radius = shapeSize / 2;
        cx = (left + shapeSize) / 2;
        cy = shapeSize / 2;
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        setShapeColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
