package com.project.databinding_moudle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.project.databinding_moudle.R
import com.project.databinding_moudle.databinding.ActivityDataBindingTest2Binding
import org.json.JSONStringer

class DataBindingTest2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindingTest2Binding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_data_binding_test2,null,false)
        setContentView(binding.root)
        binding.title = "title"
    }
}
