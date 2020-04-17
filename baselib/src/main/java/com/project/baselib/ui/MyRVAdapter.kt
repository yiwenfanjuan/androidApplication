package com.project.baselib.ui

import android.content.Context
import androidx.databinding.ViewDataBinding
import kotlin.properties.Delegates

/**
 *@time 2020/4/8
 *@user 张一凡
 *@description
 *@introduction
 */
open class MyRVAdapter<D,T: ViewDataBinding> : BaseRVAdapter<D, T> {

    private var layoutResource: Int

    constructor(context: Context,layoutResource: Int,datas: List<D>):super(context){
        this.items = datas;
        this.layoutResource = layoutResource
    }

    override fun layoutId(): Int = layoutResource
}