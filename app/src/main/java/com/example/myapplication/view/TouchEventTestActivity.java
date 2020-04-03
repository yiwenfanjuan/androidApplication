package com.example.myapplication.view;


import android.util.Log;
import android.view.View;
import com.project.baselib.ui.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTouchEventTestBinding;

public class TouchEventTestActivity extends BaseActivity<ActivityTouchEventTestBinding> {

    private static final String TAG = TouchEventTestActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_touch_event_test;
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
        Log.e(TAG, String.valueOf(view.getTag()));
    }
}
