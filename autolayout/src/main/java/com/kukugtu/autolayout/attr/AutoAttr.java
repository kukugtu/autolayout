package com.kukugtu.autolayout.attr;

import android.view.View;

import com.kukugtu.autolayout.utils.DisplayUtil;

public abstract class AutoAttr {
    protected int pxVal;

    public AutoAttr(int pxVal) {
        this.pxVal = pxVal;
    }

    public void apply(View view) {

        int val = DisplayUtil.getAutoSize(pxVal);
        execute(view, val);
    }



    protected abstract void execute(View view, int val);

}
