package com.kukugtu.autolayout.attr;

import android.view.View;

public class MinHeightAttr extends AutoAttr {
    public MinHeightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        view.setMinimumHeight(val);
    }

}
