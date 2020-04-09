package com.project.databinding_moudle.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

/**
 *@time 2020/4/9
 *@user 张一凡
 *@description
 *@introduction
 */
@BindingMethods(value = [
    BindingMethod(
            type = TextView::class,
            attribute = "toast",
            method = "showTextToast"
    )
])
class ToastTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun showTextToast(text: String){
        Toast.makeText(context, text,Toast.LENGTH_SHORT).show()
    }
}