package com.project.databinding_moudle.data

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import com.project.databinding_moudle.BR

/**
 *@time 2020/4/7
 *@user 张一凡
 *@description
 *@introduction
 */
class Teacher : Observable {

    private val registry = PropertyChangeRegistry()

    @get:Bindable
    var name = ""
        set(value) {
            field = value
            registry.notifyChange(this, BR.name)
        }
    @get:Bindable
    var course = ""
        set(value) {
            field = value
            registry.notifyChange(this,BR.course)
        }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }
}