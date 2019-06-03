package com.kukugtu.autolayout.attr;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class TextSizeAttr extends AutoAttr {

    public TextSizeAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view instanceof TextView))
            return;
        ((TextView) view).setIncludeFontPadding(false);
        ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, val);
    }


}
