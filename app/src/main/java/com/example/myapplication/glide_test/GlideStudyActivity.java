package com.example.myapplication.glide_test;

import android.content.Intent;
import android.view.View;

import com.project.baselib.ui.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityGlideStudyBinding;

public class GlideStudyActivity extends BaseActivity<ActivityGlideStudyBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide_study;
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
            case R.id.btn_glide_1:
                startActivity(new Intent(this,GlideTest1Activity.class));
                break;
        }
    }
}
