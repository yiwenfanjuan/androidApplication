package com.project.baselib.ui

import android.app.Application
import android.content.Context

/**
 *@time 2020/4/15
 *@user 张一凡
 *@description
 *@introduction
 */
class BaseApplication: Application() {

    companion object {

        lateinit var instance: BaseApplication
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }
}