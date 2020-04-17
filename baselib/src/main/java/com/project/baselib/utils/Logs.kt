package com.project.baselib.utils

import android.app.Application
import android.content.Context
import android.util.Log
import com.project.baselib.ui.BaseApplication

/**
 *@time 2020/4/15
 *@user 张一凡
 *@description 打印日志类
 *@introduction
 */
class Logs {

    companion object {

        @JvmStatic
        fun e(tag: String, content: String){
            Log.e(tag,content)
        }

        @JvmStatic
        fun e(content: String){
            val tag = BaseApplication.instance.packageName
            e(tag,content)
        }
    }
}