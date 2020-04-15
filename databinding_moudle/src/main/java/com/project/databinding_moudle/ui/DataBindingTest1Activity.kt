package com.project.databinding_moudle.ui

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.util.set
import com.project.baselib.ui.BaseActivity
import com.project.databinding_moudle.R
import com.project.databinding_moudle.data.User
import com.project.databinding_moudle.databinding.ActivityDataBindingTest1Binding
import com.project.databinding_moudle.utils.ClickHandler
import com.project.databinding_moudle.utils.Presenter
import java.io.*

/**
*@date: 2020/3/30
*@author: 张一凡
*@description: 数据绑定库演示
*@introduction: 数据绑定库简单演示类，参考文档: https://developer.android.com/topic/libraries/data-binding
*/
class DataBindingTest1Activity : BaseActivity<ActivityDataBindingTest1Binding>(), View.OnClickListener {


    override fun getLayoutId(): Int  = R.layout.activity_data_binding_test1

    override fun initUi() {

    }

    override fun initData() {
        val user: User = User("name",20,"1232142134",null)
        findViewById<TextView>(R.id.tv_content1).apply {
            text = user.name
        }
        mBinding.user = user

        //创建一个List
        val list = mutableListOf<String>()
        list.add("哈哈哈")
        list.add("嘻嘻嘻")
        list.add("哼哼哼")

        //创建长度为3，默认值为空的定长数组
        val sparse = Array<String>(3){""}
        sparse[0] = "呵呵呵"
        sparse[1] = "呸呸呸"
        //可以通过set()方法设置指定位置的参数
        sparse.set(2,"滚滚滚")

        //创建指定初始值的数组
        val sparse1 = intArrayOf(1,2,3,4)

        //创建数组
        val array = SparseArray<String>()
        array.put(0,"呵呵呵")
        array.put(1,"呸呸呸")
        array.put(2,"滚滚滚")

        //创建Map
        val map = mutableMapOf<String,String>()
        map.set("哈","hahah")
        map["哼"] = "哼哼哼"
        map["滚"] = "滚滚滚"

        mBinding.list = list
        mBinding.sparse = array
        mBinding.map = map

        mBinding.index = 1
        mBinding.key = "滚"

        mBinding.largePadding = true

        mBinding.doClick = this

        val clickHandler: ClickHandler = ClickHandler()
        mBinding.clickHandler = clickHandler

        val task = Runnable {
            val sp: SharedPreferences = this.getSharedPreferences("test", Context.MODE_PRIVATE)
            val edit = sp.edit()
            edit.putString("name",user.name)
            edit.putInt("age",user.age)
            edit.putString("phone",user.phone)
            edit.apply()
            edit.commit()




            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)

            for(i in permissions){
                if(ActivityCompat.checkSelfPermission(this,i) == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(this,permissions,100)
                    return@Runnable
                }
            }
            Log.e("TAG","所有权限已经申请")

            val userInfo = "userInfo:name:"+user.name+",age:"+user.age+",phone"+user.phone
            val file = File(Environment.getDataDirectory().absolutePath + "/data/com.example.myapplication/project")
            if(!file.exists()) {
                if (file.mkdir()){
                    Log.e("TAG","创建文件夹成功")
                }else{
                    Log.e("TAG","创建文件夹失败")
                    return@Runnable
                }
            }
            val userInfoFile = File(file.absolutePath,"user.txt")
            val output = FileOutputStream(userInfoFile)
            val outputBuffer = BufferedOutputStream(output)
            val bufferWriter = BufferedWriter(OutputStreamWriter(outputBuffer))
            bufferWriter.write(userInfo)
            bufferWriter.flush()
            bufferWriter.close()

        }

        val presenter: Presenter = Presenter()

        mBinding.task = task
        mBinding.presenter = presenter

    }

    override fun onClick(v: View?) {
        doClick(v)
    }

}
