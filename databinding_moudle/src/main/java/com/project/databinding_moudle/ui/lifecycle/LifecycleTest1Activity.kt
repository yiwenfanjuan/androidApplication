package com.project.databinding_moudle.ui.lifecycle
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleRegistry
import com.project.baselib.ui.BaseActivity
import com.project.baselib.utils.Logs
import com.project.databinding_moudle.R
import com.project.databinding_moudle.databinding.ActivityLifecycleTest1Binding

class LifecycleTest1Activity : BaseActivity<ActivityLifecycleTest1Binding>() {

    private lateinit var locationUtils: LocationUtils


    override fun getLayoutId(): Int = R.layout.activity_lifecycle_test1

    override fun initUi() {

    }

    override fun initData() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationUtils = LocationUtils(this,lifecycle){
            Log.e("TAG","获取到的位置信息$it")
        }
        lifecycle.addObserver(locationUtils)

        Logs.e("onCreate() finish")
    }


    override fun onStart() {
        super.onStart()
        Logs.e("onStart()")
    }

    override fun onResume() {
        super.onResume()
        Logs.e("onResume()")
    }

    override fun onPause() {
        super.onPause()
        Logs.e("onPause()")
    }

    override fun onStop() {
        super.onStop()
        Logs.e("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logs.e("onDestroy()")
    }

}
