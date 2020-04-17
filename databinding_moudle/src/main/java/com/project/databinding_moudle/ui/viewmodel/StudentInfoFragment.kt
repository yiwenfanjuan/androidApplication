package com.project.databinding_moudle.ui.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.project.databinding_moudle.data.Student
import com.project.databinding_moudle.data.User
import com.project.databinding_moudle.databinding.FragmentStudentInfoBinding
import java.lang.Exception

/**
 *@time 2020/4/17
 *@user 张一凡
 *@description
 *@introduction
 */
class StudentInfoFragment: Fragment() {

    private lateinit var model: ShareUserViewModel
    private lateinit var mBinding: FragmentStudentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this)[ShareUserViewModel::class.java]
        }?:throw Exception("Activity尚未初始化")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentStudentInfoBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.select.observe(this,Observer<Student>{
            mBinding.student = it
        })
    }
}