package com.kukugtu.autolayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.DisplayUtil;

/**
 * @date 2018/9/27 10:00
 * @author kukugtu
 */

public class MainActivity extends BaseActivity {

    protected static int flag = 0;
    private AutoLinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.setStatusBarLeave((ViewGroup) findViewById(R.id.base_view), this);
        if(flag%2==0){
            getRootView().setBackgroundResource(R.drawable.timg4);
            Util.setStatusBarTextStyle(this, Util.LIGHT_TEXTCOLOR);
        }else {
            getRootView().setBackgroundResource(R.drawable.timg3);
            Util.setStatusBarTextStyle(this, Util.NEIGHT_TEXTCOLOR);
        }
        flag++;



        findViewById(R.id.confirm_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        findViewById(R.id.later_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        view = findViewById(R.id.linearlayout);
        TextView textView = new TextView(this);
        textView.setText("我是从代码中添加的View");
        textView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(
                (int)(DisplayUtil.getRateHei(this)*540),
                (int) (DisplayUtil.getRateWid(this)*540));
        params.leftMargin = (int)(DisplayUtil.getRateHei(this)*270);
        params.bottomMargin = (int)(DisplayUtil.getRateHei(this)*270);
        params.topMargin = (int)(DisplayUtil.getRateHei(this)*270);
        view.addView(textView,params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag--;
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
