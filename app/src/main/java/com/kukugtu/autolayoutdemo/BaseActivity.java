package com.kukugtu.autolayoutdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Field;


/**
 * @author kukugtu
 * @date 2018/9/27 10:00
 */

public abstract class BaseActivity extends AutoLayoutActivity implements SlidingPaneLayout.PanelSlideListener {

    private boolean isOnrestart = false;
    protected MySlideMenu slidingPaneLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isOnrestart = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isOnrestart) {
            isOnrestart = false;
            initSwipeBackFinish();
        }
    }

    /**
     * 初始化滑动返回
     */
    private void initSwipeBackFinish() {
        if (getSupportSwipeBack()) {
            slidingPaneLayout = new MySlideMenu(this);
            //通过反射改变mOverhangSize的值为0，这个mOverhangSize值为菜单到右边屏幕的最短距离，默认
            //是32dp，现在给它改成0
            try {
                Field fOverhang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                fOverhang.setAccessible(true);
                fOverhang.set(slidingPaneLayout, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            slidingPaneLayout.setPanelSlideListener(this);
            slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

            View leftView = new View(this);
            leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            slidingPaneLayout.addView(leftView, 0);

            ViewGroup decor = (ViewGroup) getRootView().getParent();
            ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                decorChild.setBackground(getRootView().getBackground());
            } else {
                decorChild.setBackgroundColor(getResources().getColor(android.R.color.black));
            }
            decor.removeView(decorChild);
            slidingPaneLayout.addView(decorChild, 1);
            decor.addView(slidingPaneLayout, 0);

        }
    }

    @Override
    public void onPanelClosed(@NonNull View view) {

    }

    @Override
    public void onPanelOpened(@NonNull View view) {
        finish();
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onPanelSlide(@NonNull View view, float v) {

    }

    /**
     * 返回rootview，用于滑动返回
     *
     * @return rootview的id
     */
    public abstract View getRootView();

    /**
     * @return 是否支持滑动返回
     */
    protected boolean getSupportSwipeBack() {
        return false;
    }

}

