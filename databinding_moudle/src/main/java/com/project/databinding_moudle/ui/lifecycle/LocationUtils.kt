package com.project.databinding_moudle.ui.lifecycle

import android.content.Context
import android.location.Location
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
internal class LocationUtils(
        private val context: Context,
        private val lifecycle: Lifecycle,
        private val callback: (Location) -> Unit
) : LifecycleObserver {
    private var enable = false

    //在onStart中开始请求
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        //开始请求位置信息
        if (enable)
            Logs.e("开始请求")
    }

    //在onStop之后停止请求
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        //停止请求位置信息
        Logs.e("停止请求")
    }

    fun enable() {
        enable = true
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            //如果没有开始连接，则在这里开始连接
            start()
        }
    }
}