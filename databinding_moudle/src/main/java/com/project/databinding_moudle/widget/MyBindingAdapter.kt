package com.project.databinding_moudle.widget

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *@time 2020/4/9
 *@user 张一凡
 *@description
 *@introduction
 */
class MyBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["setMarginLeft"])
        fun setMarginLeft(view: View, marginLeft: Int) {
            if (view.layoutParams is ViewGroup.MarginLayoutParams) {
                val params = view.layoutParams as ViewGroup.MarginLayoutParams
                params.leftMargin = marginLeft
                view.layoutParams = params
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["url","error","placeHolder"],requireAll = false)
        fun loadImage(view: ImageView,url: String?,error: Drawable?,placeHolder: Drawable){
            Glide.with(view.context)
                    .asBitmap()
                    .load(url)
                    .error(error)
                    .placeholder(placeHolder)
                    .into(view)
        }

        @JvmStatic
        @BindingAdapter(value = ["android:paddingLeft","newPadding"],requireAll = false)
        fun setPaddingLeft(view: View,oldPadding: Int,newPadding: Int){
            if(oldPadding != newPadding){
                view.setPadding(newPadding,
                                view.paddingTop,
                                view.paddingRight,
                                view.paddingBottom
                        )
            }
        }
    }
}