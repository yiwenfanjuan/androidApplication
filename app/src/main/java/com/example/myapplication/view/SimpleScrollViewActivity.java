package com.example.myapplication.view;

import com.project.baselib.ui.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySimpleScrollViewBinding;

public class SimpleScrollViewActivity extends BaseActivity<ActivitySimpleScrollViewBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_simple_scroll_view;
    }

    @Override
    protected void initUi() {
        //加载动画
        //mBinding.view1.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));

        //缓慢滑动
        mBinding.view1.smoothScrollTo(400,-800);
    }

    @Override
    protected void initData() {

    }
}
