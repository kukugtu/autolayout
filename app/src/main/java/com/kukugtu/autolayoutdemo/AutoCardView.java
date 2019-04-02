package com.kukugtu.autolayoutdemo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.kukugtu.autolayout.AutoFrameLayout;
import com.kukugtu.autolayout.utils.AutoLayoutHelper;

public class AutoCardView extends CardView {

    public AutoCardView(Context context) {
        super(context);
    }

    public AutoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public AutoFrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoFrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            AutoLayoutHelper.adjustChildren(this);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
