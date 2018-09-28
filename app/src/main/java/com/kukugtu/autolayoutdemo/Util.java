package com.kukugtu.autolayoutdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.DisplayUtil;

/**
 * @author kukugtu
 * @date 2018/9/27 10:00
 */

public class Util {

    public static final int LIGHT_TEXTCOLOR = 1;
    public static final int NEIGHT_TEXTCOLOR = 2;

    private Util() {
    }

    public static void setStatusBarLeave(ViewGroup baseView, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) baseView.getLayoutParams();
            int hei = DisplayUtil.getStatusHeight(context);
            lp.setMargins(0, hei, 0, 0);
            baseView.setLayoutParams(lp);
        }
    }

    public static void setStatusBarTextStyle(Activity activity, int status) {
        if (status == NEIGHT_TEXTCOLOR) {
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (status == LIGHT_TEXTCOLOR) {
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
