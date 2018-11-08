package com.kukugtu.autolayoutdemo;

import android.app.Activity;
import android.view.View;

/**
 * @author kukugtu
 * @date 2018/9/27 10:00
 */

public class Util {

    public static final int LIGHT_TEXTCOLOR = 1;
    public static final int NEIGHT_TEXTCOLOR = 2;

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
