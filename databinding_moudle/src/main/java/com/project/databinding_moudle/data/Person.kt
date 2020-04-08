package com.project.databinding_moudle.data

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt

/**
 *@time 2020/4/7
 *@user 张一凡
 *@description Person类，测试可观察字段
 *@introduction
 */
class Person {
    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val age = ObservableInt()
    val height = ObservableFloat()
    val isBoy = ObservableBoolean()
}