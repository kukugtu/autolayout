package com.kukugtu.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

public class HeightAttr extends AutoAttr {
    public HeightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = val;
    }


}
