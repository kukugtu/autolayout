package com.kukugtu.autolayoutdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kukugtu.autolayout.config.AutoLayoutConifg;
import com.kukugtu.autolayout.utils.DisplayUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExtraAdapter());
    }

    private class ExtraAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_extra, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayoutparams(newConfig);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        updateLayoutparams(newConfig);
    }

    private void updateLayoutparams(Configuration newConfig) {
        //重新计算尺寸，没有代码中添加的View和修改的属性，只需要改变设计图尺寸
        int wid = DisplayUtil.dp2px(this, newConfig.screenWidthDp);
        int hei = DisplayUtil.dp2px(this, newConfig.screenHeightDp);
        AutoLayoutConifg.getInstance().initScreen(wid,
                hei,
                DisplayUtil.getMetaDataWid(this),
                DisplayUtil.getMetaDataHei(this));
    }
}
