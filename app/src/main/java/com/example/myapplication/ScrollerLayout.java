package com.example.myapplication;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ScrollerLayout extends ViewGroup {
    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;
    /**
     * 判断为拖动的最小移动像素数
     */
    private int mTouchSlop;
    /**
     * 手指按下时屏幕的坐标
     */
    private float mXDown;
    /**
     * 手指当时所处的屏幕坐标
     */
    private float mXMove;
    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;
    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;
    /**
     * 界面
     */
    private int rightBorder;

    public ScrollerLayout(Context context) {
        super(context);
        init();
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //初始化操作
    private void init() {
        mScroller = new Scroller(getContext());
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        //获取认为是最小滑动的值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量每一个子控件的大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
                //初始化左右边界值
                leftBorder = getChildAt(0).getLeft();
                rightBorder = getChildAt(getChildCount() - 1).getRight();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                if (diff > mTouchSlop) {
                    //当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件事件
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                //当手指抬起时。根据当前的滚动值来判断应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                //调用startScroll()方法来初始化滚动数据并刷新页面
                mScroller.startScroll(getScrollX(),0,dx,0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);

    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }
}
