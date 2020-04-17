package com.project.baselib.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.project.baselib.BR
import com.project.baselib.utils.Logs

/**
 *@time 2020/4/8
 *@user 张一凡
 *@description
 *@introduction
 */
abstract class BaseRVAdapter<D, T : ViewDataBinding>(val context: Context) : RecyclerView.Adapter<BaseRViewHolder<T>>(),View.OnClickListener {
    //数据列表
    var items: List<D> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRViewHolder<T> {
        //加载视图
        val view = LayoutInflater.from(context).inflate(layoutId(), parent, false)
        return BaseRViewHolder(view)
    }

    //返回列表数量
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseRViewHolder<T>, position: Int) {
        val itemData: D = items[position]
        loadData(itemData, holder.binding, position)
    }

    //设置数据
    open fun loadData(data: D, binding: T, position: Int) {
        binding.setVariable(BR.item, data)
        binding.setVariable(BR.position, position)
        binding.setVariable(BR.doClick,object : View.OnClickListener{
            override fun onClick(v: View?) {
                v?.let {
                    doClick(it,position)
                }
            }

        })
        binding.root.setOnClickListener {
            doClick(it,position)
        }
    }

    //获取itemView的布局
    abstract fun layoutId(): Int

    //设置数据
    fun setData(list: List<D>) {
        this.items = list
    }

    override fun onClick(v: View?) {
        v?.let {

        }
    }

    fun getItem(position: Int):D = items[position]

    open fun doClick(view: View, position: Int){
    }
}