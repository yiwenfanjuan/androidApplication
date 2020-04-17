package com.project.baselib.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.project.baselib.BR

/**
 *@time 2020/4/8
 *@user 张一凡
 *@description
 *@introduction
 */
class BaseRViewHolder<T : ViewDataBinding> : RecyclerView.ViewHolder {

    //dataBinding
    var binding: T
    

    constructor(itemView: View) : super(itemView) {
        binding = DataBindingUtil.bind<T>(itemView)!!

    }


}