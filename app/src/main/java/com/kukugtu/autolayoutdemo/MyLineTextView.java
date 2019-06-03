package com.kukugtu.autolayoutdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kukugtu.autolayout.utils.DisplayUtil;

public class MyLineTextView extends View {

    private int lineColor;
    private int textColor;
    private float lineSize;
    private float inTextSize;
    private String text;

    private Paint paint;
    private int width;
    private int height;

    public MyLineTextView(Context context) {
        this(context, null);
    }

    public MyLineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.MyLineTextView);
        if (ta != null) {
            lineColor = ta.getColor(R.styleable.MyLineTextView_lineColor, 0);
            textColor = ta.getColor(R.styleable.MyLineTextView_inTextColor, 0);
            inTextSize = ta.getDimension(R.styleable.MyLineTextView_inTextSize, 50);
            text = ta.getString(R.styleable.MyLineTextView_text);
            lineSize = ta.getDimension(R.styleable.MyLineTextView_lineSize, 50);
            ta.recycle();
        }
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(lineColor);
        paint.setStrokeWidth(DisplayUtil.getAutoSize(lineSize));
        canvas.drawLine(0, height - (DisplayUtil.getAutoSize(lineSize)) / 2, width, height - (DisplayUtil.getAutoSize(lineSize)) / 2, paint);

        paint.setTextSize(DisplayUtil.getAutoSize(inTextSize));
        paint.setColor(textColor);
        Paint.FontMetricsInt centerfontMetricsInt = paint.getFontMetricsInt();
        canvas.drawText(text, 0, height - (DisplayUtil.getAutoSize(lineSize)) - centerfontMetricsInt.descent, paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}


