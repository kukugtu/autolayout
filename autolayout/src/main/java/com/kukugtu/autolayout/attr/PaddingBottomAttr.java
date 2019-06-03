package com.kukugtu.autolayout.attr;

import android.view.View;

public class PaddingBottomAttr extends AutoAttr {
    public PaddingBottomAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        view.setPadding(l, t, r, val);

    }
}
