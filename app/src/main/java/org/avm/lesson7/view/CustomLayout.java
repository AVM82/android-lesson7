package org.avm.lesson7.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomLayout extends ViewGroup {

    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int childCount = getChildCount();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int maxWidth = 0;
        int maxHeight = 0;
        int lineWidth = 0;
        int childState = 0;

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                if (lineWidth + childWidth > widthSize) {
                    lineWidth = childWidth;
                    maxHeight += childHeight;
                } else {
                    lineWidth += childWidth;
                    maxHeight = Math.max(maxHeight, childHeight);
                }
                maxWidth = Math.max(maxWidth, lineWidth);
                childState = combineMeasuredStates(childState, child.getMeasuredState());
            }
        }
        maxWidth = (widthMode == MeasureSpec.EXACTLY) ? widthSize : maxWidth;
        maxHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize : maxHeight;
        setMeasuredDimension(maxWidth, maxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int childCount = getChildCount();
        int widthSize = getMeasuredWidth();
        int childWidth;
        int childHeight;
        int childLeft = 0;
        int childTop = 0;
        int widthLine = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                childWidth = child.getMeasuredWidth();
                childHeight = child.getMeasuredHeight();
                if (widthLine + childWidth > widthSize) {
                    widthLine = childWidth;
                    childTop += childHeight;
                    childLeft = 0;
                } else {
                    widthLine += childWidth;
                }
                child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
                childLeft += childWidth;
            }
        }
    }
}
