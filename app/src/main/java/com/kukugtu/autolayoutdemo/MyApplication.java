package com.kukugtu.autolayoutdemo;

import android.app.Application;

import com.kukugtu.autolayout.config.AutoLayoutConifg;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().init(this);
    }
}
