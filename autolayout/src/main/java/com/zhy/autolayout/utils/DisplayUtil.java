package com.zhy.autolayout.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.zhy.autolayout.config.AutoLayoutConifg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kukugtu on 2017/11/7 0007 9:40.
 */

public class DisplayUtil {


    //获取Manifest设定的宽度 目前还没用
    public static int getMetaDataHei(Context context) {
        return (int) (getMetaDataValue(context, "design_height"));
    }

    //获取anifest设定高度
    public static int getMetaDataWid(Context context) {
        return (int) (getMetaDataValue(context, "design_width"));
    }

    //获取设定与设备屏幕宽度比例
    public static float getRateWid() {
        int displayWid = AutoLayoutConifg.getInstance().getScreenWidth();
        float designWid = AutoLayoutConifg.getInstance().getDesignWidth();
        return displayWid / designWid;
    }

    //获取设定与设备屏幕高比例
    public static float getRateHei() {
        int displayHei = AutoLayoutConifg.getInstance().getScreenHeight();
        float designHeight = AutoLayoutConifg.getInstance().getDesignHeight();
        return displayHei / designHeight;
    }

    //px转sp 设置代码中设置字体大小时，好像大小事sp
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

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



    static boolean isPxVal(TypedValue val) {
        if (val != null && val.type == TypedValue.TYPE_DIMENSION &&
                getComplexUnit(val.data) == TypedValue.COMPLEX_UNIT_PX) {
            return true;
        }
        return false;
    }

    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data >> TypedValue.COMPLEX_UNIT_SHIFT);
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
}
