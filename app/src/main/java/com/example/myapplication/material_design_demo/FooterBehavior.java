package com.example.myapplication.material_design_demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private static final String TAG = FooterBehavior.class.getSimpleName();

    //TextView距离下面的高度
    private int marginBottom = -1;
    //TextView的高度
    private int mHeight = -1;

    private boolean isInit = false;

    //滑动的距离
    private int mTotalScroll = 0;


    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //这里我们只关心竖直方向的滚动
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

        /*
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (params.bottomMargin >= -mHeight && params.bottomMargin <= marginBottom) {
            params.bottomMargin -= dy;
            if (params.bottomMargin < -mHeight) {
                params.bottomMargin = -mHeight;
            } else if (params.bottomMargin > marginBottom) {
                params.bottomMargin = marginBottom;
            }
        }
        child.setLayoutParams(params);
         */

        //同时也可以根据滑动的距离设置View的Alpha值
        mTotalScroll += dy;
        float alpha = (float) mTotalScroll / (float) (marginBottom + mHeight);
        if (alpha >= 0 && alpha <= 1) {
            child.setAlpha(alpha);
        } else if(alpha < 0){
            mTotalScroll = 0;
        }else{
            mTotalScroll = marginBottom + mHeight;
        }


        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (!isInit) {
            marginBottom = params.bottomMargin;
            mHeight = child.getMeasuredHeight();
            isInit = true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }
}
