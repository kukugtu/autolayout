package com.kukugtu.autolayoutdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.autolayout.utils.DisplayUtil;

/**
 * @author kukugtu
 * @date 2018/9/27 10:00
 */

public class MainActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Util.setStatusBarLeave((ViewGroup) findViewById(R.id.base_view), this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Util.setStatusBarTextStyle(this, Util.LIGHT_TEXTCOLOR);
        }
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);


        //设置居中的一个TextView
        AutoLinearLayout viewGroup = findViewById(R.id.linearlayout);
        TextView textView = new TextView(this);
        textView.setText("我是从代码中添加的View");
        textView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(540, 540);
        params.leftMargin = 270;
        params.bottomMargin = 270;
        params.topMargin = 270;
        textView.setLayoutParams(params);
        AutoUtils.auto(textView);
        viewGroup.addView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ExtraActivity.class));
            }
        });


        //更改View的属性
        TextView change_attr = findViewById(R.id.change_attr);
        //此处需要注意，原本是谁的layoutparams就用谁的，如果用了ViewGroup的可能会出现属性丢失
        AutoLinearLayout.LayoutParams layoutParams = new AutoLinearLayout.LayoutParams(
                (AutoLinearLayout.LayoutParams) change_attr.getLayoutParams());
        //可不用DisplayUtil用AutoUtils
        layoutParams.width = (int) (600 * DisplayUtil.getRateWid());
        change_attr.setLayoutParams(layoutParams);

        //        layoutParams.width = 600;
        //        change_attr.setLayoutParams(layoutParams);
        //        AutoUtils.auto(change_attr);

        //更改自定义View的属性
        MyLineTextView my_view  = findViewById(R.id.my_view);
        AutoLinearLayout.LayoutParams myLayoutParams = new AutoLinearLayout.LayoutParams(
                (AutoLinearLayout.LayoutParams) change_attr.getLayoutParams());
        myLayoutParams.width = (int) (300 * DisplayUtil.getRateWid());
        my_view.setLayoutParams(myLayoutParams);

    }
}
