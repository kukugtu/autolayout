package com.kukugtu.autolayout.attr;

import android.view.View;

public class PaddingRightAttr extends AutoAttr {
    public PaddingRightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, val, b);

    }
}
