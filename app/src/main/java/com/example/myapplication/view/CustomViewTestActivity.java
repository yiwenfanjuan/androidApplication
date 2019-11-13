package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCustomViewTestBinding;

public class CustomViewTestActivity extends BaseActivity<ActivityCustomViewTestBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_view_test;
    }

    @Override
    protected void initUi() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void doClick(View view) {
        super.doClick(view);
        switch (view.getId()){
            case R.id.btn_simple_scroll:
                //简单的View滚动的方式
                startActivity(new Intent(this,SimpleScrollViewActivity.class));
                break;
            case R.id.btn_touch_test:
                //点击事件演示
                startActivity(new Intent(this,TouchEventTestActivity.class));
                break;
            case R.id.btn_custom_view_group:
                //自定义ViewGroup
                startActivity(new Intent(this,CustomHorizontalViewTestActivity.class));
                break;
        }
    }
}
