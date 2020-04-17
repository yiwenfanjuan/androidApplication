package com.project.databinding_moudle.ui.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *@time 2020/4/16
 *@user 张一凡
 *@description
 *@introduction
 */
class NameViewModel: ViewModel() {
    //创建一个包含String类型的LiveData
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}