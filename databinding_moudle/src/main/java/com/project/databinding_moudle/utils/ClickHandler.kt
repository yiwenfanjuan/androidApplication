package com.project.databinding_moudle.utils

import android.util.Log
import android.view.View
import com.project.databinding_moudle.R
import kotlinx.android.synthetic.main.activity_data_binding_test1.*

/**
 *@time 2020/4/2
 *@user 张一凡
 *@description 处理点击事件
 *@introduction
 */
class ClickHandler {

    fun click(view: View){
        if(view.id == R.id.tv_content10){
            Log.e("TAG","View被点击")
        }
    }
}