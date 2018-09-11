package org.avm.lesson7.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class RectShape extends Shape {
    Rect rect;
    public RectShape(Context context) {
        super(context);
    }

    public RectShape(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    void init(@Nullable AttributeSet set) {
        super.init(set);
        rect = new Rect();
        rect.left = left;
        rect.top = top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rect.right = rect.left + shapeSize;
        rect.bottom = rect.top + shapeSize;
        canvas.drawRect(rect, paint);

    }
}
