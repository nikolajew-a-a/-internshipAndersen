package com.example.android.topic51;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class CustomCircleView extends View {
    private static final int DEFAULT_CIRCLE_COLOR = Color.BLUE;

    private int circleColor;
    private Paint paint;
    private float radius;
    private int color;


    public CustomCircleView(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @NonNull AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomCircleView);
        radius = typedArray.getDimension(R.styleable.CustomCircleView_circleRadius,0);
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        invalidate();
    }

    public int getCircleColor() {
        return circleColor;
    }

    @Override
    public void setBackground(@NonNull Drawable background) {
        color = DEFAULT_CIRCLE_COLOR;
        if (background instanceof ColorDrawable) {
            color = ((ColorDrawable) background).getColor();
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        int pl = getPaddingLeft();
        int pr = getPaddingRight();
        int pt = getPaddingTop();
        int pb = getPaddingBottom();

        int usableWidth = w - (pl + pr);
        int usableHeight = h - (pt + pb);

        int cx = pl + (usableWidth / 2);
        int cy = pt + (usableHeight / 2);

        paint.setColor(color);
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
