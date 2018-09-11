package org.avm.lesson7.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import org.avm.lesson7.R;

public abstract class Shape extends View {
    private static final int SHAPE_SIZE_DEF = 100;
    protected Paint paint;
    protected int shapeColor;
    protected int shapeSize;
    protected int left;
    protected int top;

    ObjectAnimator animatorX;
    ObjectAnimator animatorY;
    AnimatorSet animatorSet1;


    public Shape(Context context) {
        super(context);
        init(null);
    }

    public Shape(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Shape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    void init(@Nullable AttributeSet set) {
        if (set != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.Shape);
            shapeColor = typedArray.getColor(R.styleable.Shape_color, Color.GREEN);
            shapeSize = typedArray.getDimensionPixelSize(R.styleable.Shape_size, SHAPE_SIZE_DEF);
            left = typedArray.getDimensionPixelSize(R.styleable.Shape_left, 33);
            top = typedArray.getDimensionPixelSize(R.styleable.Shape_top, 33);
            typedArray.recycle();
        }
        animatorSet1 = new AnimatorSet();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setShapeColor(shapeColor);
    }

    void setShapeColor(int shapeColor) {
        paint.setColor(shapeColor);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();
                if (left < x && left + shapeSize > x) {
                    if (top < y && top + shapeSize > y) {
                        Display display = getDisplay();
                        Point size = new Point();
                        display.getSize(size);
                        float displayWidth = size.x;
                        final float displayHeight = size.y;
                        animatorX = ObjectAnimator.ofFloat(this, "translationX", displayWidth);
                        animatorY = ObjectAnimator.ofFloat(this, "translationY", displayHeight);
                        animatorSet1.playTogether(animatorX, animatorY);
                        animatorSet1.setDuration(9000);
                        animatorSet1.start();
                    }
                }
                return true;
            }
        }
        return value;
    }
}
