package com.project.databinding_moudle.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.project.databinding_moudle.BR

/**
 *@time 2020/4/7
 *@user 张一凡
 *@description
 *@introduction
 */
class Student() : BaseObservable() {

    constructor(name: String,age: Int,sid: String): this(){
        this.name = name
        this.age = age
        this.sid = sid
    }
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
    @get:Bindable
    var sid: String = "学号"
        set(value){
            field = value
            notifyPropertyChanged(BR.sid)
        }
}