package com.kukugtu.autolayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.autolayout.utils.DisplayUtil;

/**
 * @author kukugtu
 * @date 2018/9/27 10:00
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayUtil.setStatusBarLeave((ViewGroup) findViewById(R.id.base_view), this);
        getRootView().setBackgroundColor(Color.BLACK);
        Util.setStatusBarTextStyle(this, Util.LIGHT_TEXTCOLOR);


        //设置居中的一个TextView
        AutoLinearLayout view = findViewById(R.id.linearlayout);
        TextView textView = new TextView(this);
        textView.setText("我是从代码中添加的View");
        textView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        //        LinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(
        //                (int) (DisplayUtil.getRateWid(this) * 540),
        //                (int) (DisplayUtil.getRateHei(this) * 540));
        //        params.leftMargin = (int) (DisplayUtil.getRateWid(this) * 270);
        //        params.bottomMargin = (int) (DisplayUtil.getRateHei(this) * 270);
        //        params.topMargin = (int) (DisplayUtil.getRateHei(this) * 270);


        AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(540, 540);
        params.leftMargin = 270;
        params.bottomMargin = 270;
        params.topMargin = 270;

        textView.setLayoutParams(params);
        AutoUtils.auto(textView);

        view.addView(textView);
    }

    @Override
    public View getRootView() {
        return findViewById(R.id.root_view);
    }

    @Override
    protected boolean getSupportSwipeBack() {
        return true;
    }
}
