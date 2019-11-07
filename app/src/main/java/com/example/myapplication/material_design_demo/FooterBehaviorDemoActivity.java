package com.example.myapplication.material_design_demo;


import android.support.v7.widget.LinearLayoutManager;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityFooterBehaviorDemoBinding;

/**
*  @intro 演示自定义Behavior的页面
*  @author zyf
*  @date 2019/11/5
*  @descrption 在这个页面中定义了一个RecyclerView和一个底部的提示框，当RecyclerView向上滚动的时候会隐藏提示框，当RecyclerView向下滚动的时候会显示提示框
*  @version 1.0
*/

public class FooterBehaviorDemoActivity extends BaseActivity<ActivityFooterBehaviorDemoBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_footer_behavior_demo;
    }

    @Override
    protected void initUi() {
        DemoAdapter adapter = new DemoAdapter(this);
        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvContent.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }
}
