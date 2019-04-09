package com.example.myapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

/**
 * description：
 * author：yiwen
 * date：2019/4/2 下午 8:49
 * remark：
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    //绑定的dataBinding文件
    protected T mBinding;

    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this),getLayoutId(),null,false);
        setContentView(mBinding.getRoot());
        initUi();
        initData();
    }

    //设置layout文件
    protected abstract int getLayoutId();

    //初始化UI相关的数据
    protected abstract void initUi();

    //初始化数据相关
    protected abstract void initData();
}
