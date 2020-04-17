package com.project.databinding_moudle.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.databinding_moudle.data.User

/**
 *@time 2020/4/16
 *@user 张一凡
 *@description
 *@introduction
 */
class MyViewModel: ViewModel() {

    private val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<User>> = users

    private fun loadUsers(){
        //在异步线程中获取用户信息
    }
}