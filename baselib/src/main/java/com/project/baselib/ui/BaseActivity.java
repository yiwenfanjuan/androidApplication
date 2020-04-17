package com.project.baselib.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

import com.project.baselib.R;
import com.project.baselib.databinding.BaseLayoutHeaderBinding;
import java.util.Objects;

/**
 * description：
 * author：yiwen
 * date：2019/4/2 下午 8:49
 * remark：
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    //绑定的dataBinding文件
    protected T mBinding;
    //顶部
    protected BaseLayoutHeaderBinding mHeaderBinding;

    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this),getLayoutId(),null,false);
        //设置页面
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
    //点击事件
    public void doClick(View view){
        if(view.getId() == R.id.base_iv_back){
            doBack();
        }
    }

    //返回的方法
    protected void doBack(){
        onBackPressed();
    }

    //返回title
    protected TextView getTitleView(){
        TextView textView = findViewById(R.id.base_tv_title);
        Objects.requireNonNull(textView,getString(R.string.base_title_text_empty));
        return textView;
    }

    //返回键
    protected ImageView getBackView(){
        ImageView imageView = findViewById(R.id.base_iv_back);
        Objects.requireNonNull(imageView,getString(R.string.base_back_view_empty));
        return mHeaderBinding.baseIvBack;
    }

}
