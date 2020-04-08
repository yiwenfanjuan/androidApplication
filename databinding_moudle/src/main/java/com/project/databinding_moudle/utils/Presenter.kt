package com.project.databinding_moudle.utils

import android.util.Log
import android.view.View

/**
 *@time 2020/4/2
 *@user 张一凡
 *@description
 *@introduction
 */
class Presenter {

    val TAG: String = javaClass.simpleName

    fun onSaveClick(task: Runnable){
        val thread: Thread = Thread(task)
        thread.start()
    }

    fun onSaveClick(view: View, task: Runnable){
        val thread: Thread = Thread(task)
        thread.start()
    }

    fun onSaveClick(save: Boolean, task: Runnable){
        Log.e(TAG,"准备保存用户信息:$save")
        if(save){
            val thread = Thread(task)
            thread.start()
        }
    }

    fun onLongClickSave(view: View,task: Runnable): Boolean{
        val thread: Thread = Thread(task)
        thread.start()
        return true
    }
}