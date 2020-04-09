package com.project.databinding_moudle.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.baselib.ui.BaseActivity
import com.project.baselib.ui.BaseRVAdapter
import com.project.baselib.ui.MyRVAdapter
import com.project.databinding_moudle.R
import com.project.databinding_moudle.data.User
import com.project.databinding_moudle.databinding.ActivityDataBindingTest3Binding
import com.project.databinding_moudle.databinding.ItemUserInfoBinding

class DataBindingTest3Activity : BaseActivity<ActivityDataBindingTest3Binding>() {

    private lateinit var adapter: MyRVAdapter<User,ItemUserInfoBinding>

    override fun getLayoutId(): Int = R.layout.activity_data_binding_test3

    override fun initUi() {

    }

    override fun initData() {


        mBinding.rvContent.layoutManager = LinearLayoutManager(this)


        val list = mutableListOf<User>()
        for(i in 0..9){
            val user: User = User("姓名$i",i+ 1, (i + 100).toString())
            list.add(user)
        }
        adapter = MyRVAdapter(this,R.layout.item_user_info,list)
        mBinding.rvContent.adapter = adapter
        //adapter.setData(list)
        //adapter.notifyDataSetChanged()
    }
}
