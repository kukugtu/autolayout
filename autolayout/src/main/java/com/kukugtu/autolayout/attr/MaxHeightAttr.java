package com.kukugtu.autolayout.attr;

import android.view.View;

import java.lang.reflect.Method;

public class MaxHeightAttr extends AutoAttr {
    public MaxHeightAttr(int pxVal) {
        super(pxVal);
    }


    @Override
    protected void execute(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxHeight", int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }
}
