package com.kukugtu.autolayout.attr;

import android.view.View;

public class PaddingTopAttr extends AutoAttr {
    public PaddingTopAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, val, r, b);
    }
}
