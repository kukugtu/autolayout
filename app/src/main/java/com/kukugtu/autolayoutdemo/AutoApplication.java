package com.kukugtu.autolayoutdemo;

import android.app.Application;

import com.kukugtu.autolayout.config.AutoLayoutConifg;

/**
 * Create By Kukugtu on 2019/6/20 5:51 PM
 * Description: java类作用描述
 * UpdateUser:
 * UpdateDate:
 * UpdateRemark: 更新说明
 */
public class AutoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().init(this);
    }
}
