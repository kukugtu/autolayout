package com.kukugtu.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

public class MarginTopAttr extends AutoAttr {
    public MarginTopAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.topMargin = val;
    }
}
