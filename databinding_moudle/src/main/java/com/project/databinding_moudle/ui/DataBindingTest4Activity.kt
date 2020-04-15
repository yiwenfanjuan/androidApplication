package com.project.databinding_moudle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.databinding.adapters.TextViewBindingAdapter
import com.project.baselib.ui.BaseActivity
import com.project.databinding_moudle.R
import com.project.databinding_moudle.data.Student
import com.project.databinding_moudle.data.User
import com.project.databinding_moudle.databinding.ActivityDataBindingTest4Binding

class DataBindingTest4Activity : BaseActivity<ActivityDataBindingTest4Binding>() {

    private val student = Student()
    override fun getLayoutId(): Int = R.layout.activity_data_binding_test4

    override fun initUi() {
        mBinding.textChangeListener = TextViewBindingAdapter.OnTextChanged { s, _, _, _ ->
            val name: String = s.toString()
            student.name = name
        }

        val user: User = User("姓名",10,"21233","https://tse1-mm.cn.bing.net/th?id=OIP.BX8LJipOhUSQQx8GCCplWQHaIM&w=107&h=110&c=8&rs=1&qlt=90&dpr=1.5&pid=3.1&rm=2")
        mBinding.user= user
    }

    override fun initData() {
        mBinding.student = student
    }


}
