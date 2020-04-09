package com.project.databinding_moudle.ui

import android.content.Intent
import android.view.View
import com.project.baselib.ui.BaseActivity
import com.project.databinding_moudle.R
import com.project.databinding_moudle.databinding.ActivityDataBindingMainBinding


class DataBindingMainActivity : BaseActivity<ActivityDataBindingMainBinding>() {

    //布局文件
    override fun getLayoutId(): Int = R.layout.activity_data_binding_main

    //初始化UI
    override fun initUi() {
        //titleView.text = getString(R.string.data_binding_test1)
    }

    //初始化数据
    override fun initData() {

    }

    //点击事件
    override fun doClick(view: View?) {
        super.doClick(view)
        val intent: Intent = Intent()
        if (view?.id == R.id.btn_data_binding_study1) {
            intent.setClass(this, DataBindingTest1Activity::class.java)
            startActivity(intent)
        }else if(view?.id == R.id.btn_data_binding_study2){
            intent.setClass(this,DataBindingTest2Activity::class.java)
            startActivity(intent)
        }else if(view?.id == R.id.btn_data_binding_study3){
            intent.setClass(this,DataBindingTest3Activity::class.java)
            startActivity(intent)
        }
    }



}
