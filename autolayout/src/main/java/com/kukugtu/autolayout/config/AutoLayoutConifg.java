package com.kukugtu.autolayout.config;

import android.content.Context;

import com.kukugtu.autolayout.utils.DisplayUtil;

public class AutoLayoutConifg {

    private static AutoLayoutConifg sIntance = new AutoLayoutConifg();
    private static boolean inited = false;


    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    private int mScreenWidth;
    private int mScreenHeight;

    private int mDesignWidth;
    private int mDesignHeight;


    private AutoLayoutConifg() {
    }

    public void checkParams() {
        if (mDesignHeight <= 0 || mDesignWidth <= 0) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file or AutoLayoutConifg.initScreen");
        }
    }

    public static AutoLayoutConifg getInstance() {
        return sIntance;
    }


    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public int getDesignWidth() {
        return mDesignWidth;
    }

    public int getDesignHeight() {
        return mDesignHeight;
    }

    /**
     * 初始化屏幕参数
     */
    public void init(Context context,boolean reInit) {
        if(!inited||reInit){
            mScreenWidth = DisplayUtil.getDisplay(context).widthPixels;
            mScreenHeight = DisplayUtil.getDisplay(context).heightPixels;
            mDesignWidth = DisplayUtil.getMetaDataWid(context);
            mDesignHeight = mDesignWidth * mScreenHeight / mScreenWidth;
            AutoLayoutConifg.getInstance().checkParams();
            inited = true;
        }
    }

    public void init(Context context) {
        init(context,false);
    }

    /**
     * 初始化屏幕参数
     * 可在代码中更新屏幕参数和设计图尺寸，多用于旋转，缩放，分屏等操作。
     * 注意：原则上，更改尺寸请不要变动设计图宽度，否则可能会出现屏幕无法填充满的情况
     *      屏幕宽高更改过程中尽量保持比例不要变形。
     *
     * @param screenWidth   屏幕宽度
     * @param screenHei     屏幕高度
     * @param designWid     设计图宽度，尽量不变动
     * @param designHei     设计图高度，暂时无用
     */
    public void initScreen(int screenWidth,int screenHei,int designWid,int designHei){
        mScreenWidth = screenWidth;
        mScreenHeight = screenHei;
        mDesignWidth = designWid;
        mDesignHeight = mDesignWidth * mScreenHeight / mScreenWidth;
        inited = true;
    }

}
