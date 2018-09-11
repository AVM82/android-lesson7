package org.avm.lesson7.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class TriangleShape extends Shape {
    Path trianglePath;
    public TriangleShape(Context context) {
        super(context);
    }

    public TriangleShape(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TriangleShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    void init(@Nullable AttributeSet set) {
        super.init(set);
        trianglePath = new Path();
        trianglePath.moveTo(left, shapeSize);
        trianglePath.lineTo(shapeSize, shapeSize);
        trianglePath.lineTo(shapeSize / 2, top);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(trianglePath, paint);
    }
}
