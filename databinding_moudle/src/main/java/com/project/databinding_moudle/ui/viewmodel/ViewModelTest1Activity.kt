package com.project.databinding_moudle.ui.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.baselib.ui.BaseActivity
import com.project.databinding_moudle.R
import com.project.databinding_moudle.data.User
import com.project.databinding_moudle.databinding.ActivityViewModelTest1Binding

class ViewModelTest1Activity : BaseActivity<ActivityViewModelTest1Binding>() {

    private val mUserListFragment by lazy {
        StudentListFragment()
    }

    private val mUserInfoFragment by lazy {
        StudentInfoFragment()
    }

    override fun getLayoutId(): Int  = R.layout.activity_view_model_test1

    override fun initUi() {
        val model = ViewModelProviders.of(this)[MyViewModel::class.java]
        model.getUsers().observe(this,Observer<List<User>>{
            //更新UI
        })


    }

    override fun initData() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_user_list,mUserListFragment)
        transaction.replace(R.id.fl_user_info,mUserInfoFragment)
        transaction.commit()
    }
}
