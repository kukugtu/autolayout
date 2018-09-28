package com.kukugtu.autolayoutdemo;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @date 2018/9/27 10:00
 * @author kukugtu
 */

public class MySlideMenu extends SlidingPaneLayout {
    private static final float SLIDING_LENGTH = 50;
    private float downX;

    public MySlideMenu(Context context) {
        super(context, null);
    }

    public MySlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MySlideMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        //判断动作
        switch (arg0.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = arg0.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                downX = 0;
                break;
            default:
                break;
        }

        //当滑动长度小于标准时，取消这次滑动事件
        if (downX > SLIDING_LENGTH) {
            MotionEvent cancel = MotionEvent.obtain(arg0);
            cancel.setAction(MotionEvent.ACTION_CANCEL);
            return super.onInterceptTouchEvent(cancel);
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }
}