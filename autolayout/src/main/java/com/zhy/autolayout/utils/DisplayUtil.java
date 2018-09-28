package com.zhy.autolayout.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kukugtu on 2017/11/7 0007 9:40.
 */

public class DisplayUtil {

    //获取Display对象
    public static DisplayMetrics getDisplay(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        display.getMetrics(metrics);
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            return dm;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return metrics;
    }

    //获取Manifest设定的宽度
    public static int getMetaDataHei(Context context) {
        return (int) (getMetaDataValue(context, "design_height"));
    }

    //获取anifest设定高度
    public static int getMetaDataWid(Context context) {
        return (int) (getMetaDataValue(context, "design_width"));
    }

    //获取设定与设备屏幕宽度比例
    public static float getRateWid(Context context) {
        return getDisplay(context).widthPixels / ((float) getMetaDataWid(context));
    }

    //获取设定与设备屏幕高比例
    public static float getRateHei(Context context) {
        return getDisplay(context).heightPixels / ((float) getMetaDataHei(context));
    }

    //获取设定参数
    private static int getMetaDataValue(Context context, String metaDataName) {
        PackageManager pm = context.getPackageManager();
        ApplicationInfo appinfo;
        int metaDataValue = -1;
        try {
            appinfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = appinfo.metaData;
            metaDataValue = metaData.getInt(metaDataName);
            return metaDataValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metaDataValue;
    }

    //获取通知栏高度
    public static int getStatusHei(Context context) {
        int statusBarHeight2 = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight2 = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight2;
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    //用于适配通知栏的高度，需要设置多嵌套一层
    public static void setStatusBarLeave(ViewGroup baseView, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.MarginLayoutParams lp = (LinearLayout.LayoutParams) baseView.getLayoutParams();
            int hei = getStatusHeight(context);
            lp.setMargins(0, hei, 0, 0);
            baseView.setLayoutParams(lp);
        }
    }

    /**
     * 获得状态栏的高度 px
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

    //获取屏幕定义的DPI
    public static int getPingMuSize(Context mContext) {
        return getDisplay(mContext).densityDpi;
    }
}
