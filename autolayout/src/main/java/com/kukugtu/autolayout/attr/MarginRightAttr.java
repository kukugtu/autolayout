package com.kukugtu.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

public class MarginRightAttr extends AutoAttr {
    public MarginRightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.rightMargin = val;
    }

}
