package com.kukugtu.autolayout.attr;

import android.view.View;

import com.kukugtu.autolayout.utils.DisplayUtil;

public class PaddingAttr extends AutoAttr {
    public PaddingAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    public void apply(View view) {
        int all = DisplayUtil.getAutoSize(pxVal);
        view.setPadding(all, all, all, all);
    }


    @Override
    protected void execute(View view, int val) {
        view.setPadding(val, val, val, val);
    }


}
