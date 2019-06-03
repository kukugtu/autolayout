package com.kukugtu.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

import com.kukugtu.autolayout.utils.DisplayUtil;

public class MarginAttr extends AutoAttr {
    public MarginAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    public void apply(View view) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.topMargin = lp.bottomMargin = lp.leftMargin = lp.rightMargin = DisplayUtil.getAutoSize(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin = lp.rightMargin = lp.topMargin = lp.bottomMargin = val;
    }

}
