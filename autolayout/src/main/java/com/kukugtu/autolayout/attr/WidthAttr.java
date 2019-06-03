package com.kukugtu.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

public class WidthAttr extends AutoAttr {
    public WidthAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = val;
    }
}
