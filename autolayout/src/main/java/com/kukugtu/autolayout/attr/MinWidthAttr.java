package com.kukugtu.autolayout.attr;

import android.view.View;

public class MinWidthAttr extends AutoAttr {
    public MinWidthAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        view.setMinimumWidth(val);
    }
}
