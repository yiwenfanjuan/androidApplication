package com.project.databinding_moudle.utils

/**
 *@time 2020/4/2
 *@user 张一凡
 *@description
 *@introduction
 */
class Presenter {
    fun onSaveClick(task: Runnable){
        val thread: Thread = Thread(task)
        thread.start()
    }
}