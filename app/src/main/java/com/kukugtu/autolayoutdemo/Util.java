package com.kukugtu.autolayoutdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author kukugtu
 * @date 2018/9/27 10:00
 */

public class Util {

    public static final int LIGHT_TEXTCOLOR = 1;
    public static final int NEIGHT_TEXTCOLOR = 2;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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


    //用于适配通知栏的高度，需要设置全屏并多嵌套一层
    public static void setStatusBarLeave(ViewGroup baseView, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) baseView.getLayoutParams();
            int hei = getStatusHeight(context);
            lp.setMargins(0, hei, 0, 0);
            baseView.setLayoutParams(lp);
        }
    }

    /**
     * 获得状态栏的高度px
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
