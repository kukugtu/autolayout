package com.kukugtu.autolayoutdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kukugtu.autolayout.AutoLayoutActivity;
import com.kukugtu.autolayout.AutoLinearLayout;
import com.kukugtu.autolayout.config.AutoLayoutConifg;
import com.kukugtu.autolayout.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity {

    private TextView changeViewAttr;
    private MyLineTextView changeSelfViewAttr;
    private TextView codeAddView;

    //代码中添加的view需要记录下等屏幕改变时作出调整。
    //注意：屏幕改变回调需要保证activity不销毁重建，否则没有意义
    private List<View> codeViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codeViewList = new ArrayList<>();


        //设置居中的一个TextView
        AutoLinearLayout viewGroup = findViewById(R.id.linearlayout);
        codeAddView = new TextView(this);
        codeAddView.setText("我是从代码中添加的View");
        codeAddView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        updateSingleViewLayoutparams(codeAddView);
        viewGroup.addView(codeAddView);
        codeAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        codeViewList.add(codeAddView);


        //更改View的属性
        changeViewAttr = findViewById(R.id.change_attr);
        changeViewAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExtraActivity.class));
            }
        });
        updateSingleViewLayoutparams(changeViewAttr);
        codeViewList.add(changeViewAttr);


        //更改自定义View的属性
        changeSelfViewAttr = findViewById(R.id.my_view);
        updateSingleViewLayoutparams(changeSelfViewAttr);
        codeViewList.add(changeSelfViewAttr);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayoutparams(newConfig);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        updateLayoutparams(newConfig);
    }

    private void updateLayoutparams(Configuration newConfig) {
        //重新计算尺寸
        int wid = DisplayUtil.dp2px(this, newConfig.screenWidthDp);
        int hei = DisplayUtil.dp2px(this, newConfig.screenHeightDp);
        AutoLayoutConifg.getInstance().initScreen(wid,
                hei,
                DisplayUtil.getMetaDataWid(this),
                DisplayUtil.getMetaDataHei(this));

        //代码中生成view或者改变view的params，需要重新设置layoutparams
        for (View view : codeViewList) {
            updateSingleViewLayoutparams(view);
        }
    }

    private void updateSingleViewLayoutparams(View view) {
        if (view.equals(changeViewAttr)) {
            //此处需要注意，原本是谁的layoutparams就用谁的，如果用了ViewGroup的可能会出现属性丢失
            AutoLinearLayout.LayoutParams layoutParams = new AutoLinearLayout.LayoutParams(
                    (AutoLinearLayout.LayoutParams) changeViewAttr.getLayoutParams());
            layoutParams.width = (int) (600 * DisplayUtil.getRateWid());
            changeViewAttr.setLayoutParams(layoutParams);
        } else if (view.equals(codeAddView)) {
            AutoLinearLayout.LayoutParams layoutParams = new AutoLinearLayout.LayoutParams((int) (540 * DisplayUtil.getRateWid()),
                    (int) (540 * DisplayUtil.getRateHei()));
            layoutParams.leftMargin = (int) (270 * DisplayUtil.getRateWid());
            layoutParams.bottomMargin = (int) (270 * DisplayUtil.getRateHei());
            layoutParams.topMargin = (int) (270 * DisplayUtil.getRateHei());
            codeAddView.setLayoutParams(layoutParams);
            //代码中设置文字，需要转换
            codeAddView.setTextSize(DisplayUtil.px2sp(this, 50 * DisplayUtil.getRateWid()));
        } else if (view.equals(changeSelfViewAttr)) {
            AutoLinearLayout.LayoutParams layoutParams = new AutoLinearLayout.LayoutParams(
                    (AutoLinearLayout.LayoutParams) changeSelfViewAttr.getLayoutParams());
            //自定义view的改变大小不需要乘比例
            layoutParams.width = 600;
            layoutParams.height = 600;
            changeSelfViewAttr.setLayoutParams(layoutParams);
        }
    }
}
