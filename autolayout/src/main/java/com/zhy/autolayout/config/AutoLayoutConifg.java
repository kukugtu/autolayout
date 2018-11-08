package com.zhy.autolayout.config;

import android.content.Context;

import com.zhy.autolayout.utils.DisplayUtil;

public class AutoLayoutConifg {

    private static AutoLayoutConifg sIntance = new AutoLayoutConifg();


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
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.");
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


    public void init(Context context) {
        initMetaData(context);

        mScreenWidth = DisplayUtil.getDisplay(context).widthPixels;
        mScreenHeight = DisplayUtil.getDisplay(context).heightPixels;
    }

    private void initMetaData(Context context) {

        mDesignWidth = DisplayUtil.getMetaDataWid(context);
        mDesignHeight = mDesignWidth *
                DisplayUtil.getDisplay(context).heightPixels / DisplayUtil.getDisplay(context).widthPixels;

    }


}
