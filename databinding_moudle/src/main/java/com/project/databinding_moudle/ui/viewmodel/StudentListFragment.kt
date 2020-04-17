package com.project.databinding_moudle.ui.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.baselib.ui.BaseRVAdapter
import com.project.baselib.ui.MyRVAdapter
import com.project.baselib.utils.Logs
import com.project.databinding_moudle.R
import com.project.databinding_moudle.data.Student
import com.project.databinding_moudle.databinding.FragmentStudentListBinding
import com.project.databinding_moudle.databinding.ItemStudentListBinding

/**
 *@time 2020/4/17
 *@user 张一凡
 *@description
 *@introduction
 */
class StudentListFragment: Fragment() {

    private lateinit var model: ShareUserViewModel

    private lateinit var mBinding: FragmentStudentListBinding

    private val students = mutableListOf<Student>()

    private lateinit var adapter : MyRVAdapter<Student,ItemStudentListBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.let {
            ViewModelProviders.of(it)[ShareUserViewModel::class.java]
        }?:throw Exception("尚未初始化Activity")
        Logs.e("获取的Model:$model")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentStudentListBinding.inflate(inflater,null,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for(i in 1..10){
            val student = Student("姓名$i",i+10,"1232131")
            students.add(student)
        }
        mBinding.rvContent.layoutManager = LinearLayoutManager(context)
        context?.apply {
            adapter = object : MyRVAdapter<Student,ItemStudentListBinding>(this,R.layout.item_student_list,students){
                override fun doClick(view: View, position: Int) {
                    super.doClick(view, position)
                    if(view.id == R.id.tv_name){
                        Logs.e("点击名称")
                        return
                    }
                    model.select(getItem(position))
                }
            }
            mBinding.rvContent.adapter = adapter
        }

    }



}