package com.example.myapplication.RxJava;

import android.content.Intent;
import android.view.View;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRxJavaStudyBinding;

public class RxJavaStudyActivity extends BaseActivity<ActivityRxJavaStudyBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_study;
    }

    @Override
    protected void initUi() {

    }

    @Override
    protected void initData() {

    }

    public void doClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_operator1:
                intent.setClass(this, RxJavaStudyOperator1Activity.class);
                break;
            case R.id.btn_rxJava_introduction:
                intent.setClass(this,RxJavaIntroductionActivity.class);
                break;
            case R.id.btn_rxJava_filter_operation:
                intent.setClass(this,RxJavaStudyFilterOperationActivity.class);
                break;
            case R.id.btn_rxJava_merge_operator:
                intent.setClass(this,RxJavaStudyMergeOperatorActivity.class);
                break;
            case R.id.btn_condition_operator:
                intent.setClass(this,RxJavaStudyConditionOperatorActivity.class);
                break;
            case R.id.btn_transform_operator:
                intent.setClass(this,RxJavaStudyTransformOperatorActivity.class);
                break;
        }
        startActivity(intent);
    }

}
