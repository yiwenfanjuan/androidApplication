package com.project.databinding_moudle.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.databinding_moudle.data.Student
import com.project.databinding_moudle.data.User

/**
 *@time 2020/4/17
 *@user 张一凡
 *@description
 *@introduction
 */
class ShareUserViewModel: ViewModel() {
    val select = MutableLiveData<Student>()

    fun select(student: Student){
        select.value = student
    }
}