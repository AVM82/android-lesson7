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

public class Shape extends View {
    private static final int SHAPE_SIZE_DEF = 100;
    Rect rect;
    Paint paint;
    private int shapeColor;
    private int shapeSize;
    private float startX;
    private float startY;

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

    private void init(@Nullable AttributeSet set) {
        if (set != null) {
            rect = new Rect();
            TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.Shape);
            shapeColor = typedArray.getColor(R.styleable.Shape_color, Color.GREEN);
            shapeSize = typedArray.getDimensionPixelSize(R.styleable.Shape_size, SHAPE_SIZE_DEF);
            rect.left = typedArray.getDimensionPixelSize(R.styleable.Shape_left, 33);
            rect.top = typedArray.getDimensionPixelSize(R.styleable.Shape_top, 33);
            typedArray.recycle();
        }
        animatorSet1 = new AnimatorSet();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(shapeColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rect.right = rect.left + shapeSize;
        rect.bottom = rect.top + shapeSize;
        canvas.drawRect(rect, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();
                if (rect.left < x && rect.right > x) {
                    if (rect.top < y && rect.bottom > y) {
                        if (animatorSet1.isRunning()) {
                            animatorSet1.cancel();
                            this.setX(rect.left);
                            this.setY(rect.top);
                            return true;
                        }
                        Display display = getDisplay();
                        Point size = new Point();
                        display.getSize(size);
                        float displayWidth = size.x;
                        final float displayHeight = size.y;
                        animatorX = ObjectAnimator.ofFloat(this, "translationX", displayWidth);
                        animatorY = ObjectAnimator.ofFloat(this, "translationY", displayHeight);

                        animatorSet1.playTogether(animatorX, animatorY);
                        animatorX.setDuration(18000);
                        animatorY.setDuration(9000);
                        animatorSet1.start();
                    }
                }
                return true;
            }
        }
        return value;
    }
}
