package com.kukugtu.autolayout.attr;

import android.view.View;

public class PaddingLeftAttr extends AutoAttr {
    public PaddingLeftAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(val, t, r, b);

    }
}
