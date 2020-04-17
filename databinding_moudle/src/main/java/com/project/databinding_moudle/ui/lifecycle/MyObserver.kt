package com.project.databinding_moudle.ui.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.project.baselib.utils.Logs

/**
 *@time 2020/4/15
 *@user 张一凡
 *@description
 *@introduction
 */
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectionListener(){
        Logs.e("开始连接")
    }

    fun disconnectionListener(){
        Logs.e("断开连接")
    }
}