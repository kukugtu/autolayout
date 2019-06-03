package com.kukugtu.autolayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kukugtu.autolayout.AutoLayoutActivity;
import com.kukugtu.autolayout.AutoLinearLayout;
import com.kukugtu.autolayout.utils.DisplayUtil;

public class MainActivity extends AutoLayoutActivity {

    private TextView changeViewAttr;
    private MyLineTextView changeSelfViewAttr;
    private TextView codeAddView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //设置居中的一个TextView
        final AutoLinearLayout viewGroup = findViewById(R.id.linearlayout);
        codeAddView = new TextView(this);
        codeAddView.setText("我是从代码中添加的View");
        codeAddView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        //因为还没加入，只能new一个layoutparams
        AutoLinearLayout.LayoutParams codeViewLayoutParams = new AutoLinearLayout.LayoutParams(DisplayUtil.getAutoSize(540),
                DisplayUtil.getAutoSize(540));

        codeViewLayoutParams.leftMargin = DisplayUtil.getAutoSize(270);
        codeViewLayoutParams.bottomMargin = DisplayUtil.getAutoSize(270);
        codeViewLayoutParams.topMargin = DisplayUtil.getAutoSize(270);
        codeAddView.setLayoutParams(codeViewLayoutParams);
        //代码中设置文字，需要转换
        codeAddView.setTextSize(DisplayUtil.px2sp(this, DisplayUtil.getAutoSize(50)));
        viewGroup.addView(codeAddView);
        codeAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });


        //        //更改View的属性
        changeViewAttr = findViewById(R.id.change_attr);
        changeViewAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExtraActivity.class));
            }
        });

        //此处需要注意，原本是谁的layoutparams就用谁的，如果用了ViewGroup的可能会出现属性丢失
        AutoLinearLayout.LayoutParams changeAttrLayoutParams =
               new AutoLinearLayout.LayoutParams(((AutoLinearLayout.LayoutParams) changeViewAttr.getLayoutParams()));
        changeAttrLayoutParams.width = DisplayUtil.getAutoSize(600);
        changeAttrLayoutParams.height = DisplayUtil.getAutoSize(300);
//        changeAttrLayoutParams.topMargin = DisplayUtil.getAutoSize(200);
        changeViewAttr.setLayoutParams(changeAttrLayoutParams);

        changeViewAttr.setTextSize(20);


        //更改自定义View的属性
        changeSelfViewAttr = findViewById(R.id.my_view);
        AutoLinearLayout.LayoutParams selfViewChangeAttrLayoutParams =
                new AutoLinearLayout.LayoutParams((AutoLinearLayout.LayoutParams) changeSelfViewAttr.getLayoutParams());
        selfViewChangeAttrLayoutParams.width = DisplayUtil.getAutoSize(600);
        selfViewChangeAttrLayoutParams.height = DisplayUtil.getAutoSize(600);
        changeSelfViewAttr.setLayoutParams(selfViewChangeAttrLayoutParams);
    }

}
