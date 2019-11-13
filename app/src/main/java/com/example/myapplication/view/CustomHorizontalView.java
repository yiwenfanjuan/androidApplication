package com.example.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
*  @intro 自定义ViewGroup
*  @author zyf
*  @date 2019/11/13
*  @descrption 实现一个简单的类似于ViewPager的ViewGroup
*  @version 1.0
*/
public class CustomHorizontalView extends ViewGroup {

    private static final String TAG = CustomHorizontalView.class.getSimpleName();

    //记录上次事件最后的坐标
    private int lastX,lastY,lastInterceptX,lastInterceptY;

    //弹性滑动
    private Scroller mScroller;

    //当前所在的页面
    private int currentIndex = 0;

    //子View页面宽度
    private int childWidth;

    //测量滑动速度
    private VelocityTracker velocityTracker;

    public CustomHorizontalView(Context context) {
        this(context,null);
    }

    public CustomHorizontalView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomHorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mScroller = new Scroller(context);
        //初始化速度测量
        velocityTracker = VelocityTracker.obtain();
    }

    public CustomHorizontalView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if(mScroller != null && !mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //计算不同方向滑动的距离
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                //水平方向滑动的距离大于竖直方向滑动的距离
                if(Math.abs(deltaX) - Math.abs(deltaY) > 0){
                    Log.e(TAG,"拦截事件:");
                    //拦截事件
                    intercept = true;
                }else{
                    intercept = false;
                }
                break;

            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }

        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //记录当次滑动距离
                int deltaX = x - lastX;
                scrollBy(-deltaX,0);
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起的时候，判断应该滑动到哪个页面
                int distance = getScrollX() - currentIndex * childWidth;
                if(Math.abs(distance) > childWidth / 2){
                    if(distance > 0){
                        currentIndex ++;
                    }else{
                        currentIndex --;
                    }
                }

                //判断滑动速度
                else{
                    velocityTracker.computeCurrentVelocity(1000);
                    float xV = velocityTracker.getXVelocity();
                    if(Math.abs(xV) > 50){
                        if(xV > 0 ){
                            currentIndex --;
                        }   else{
                            currentIndex ++;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth,0);
                velocityTracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    //弹性滑动
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
    //弹性滑动到指定位置
    public void smoothScrollTo(int destX,int destY){
        mScroller.startScroll(getScrollX(),getScrollY(),destX - getScrollX(),destY - getScrollY(),1000);
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for(int i = 0; i < childCount; i++){
            child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                int width = child.getMeasuredWidth();
                childWidth = width;
                child.layout(left,0,left + width,child.getMeasuredHeight());
                left += width;
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        //如果没有子元素，就设置宽高为0
        if(getChildCount() == 0){
            setMeasuredDimension(0,0);
        }
        //宽和高都是AT_MOST,则宽度设置为所有子元素之和，高度设置为所有子元素中最大的那个
        else if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            int width = 0;
            int height = 0;
            for(int i = 0; i < getChildCount(); i++){
                width += getChildAt(i).getMeasuredWidth();
                if(getChildAt(i).getMeasuredHeight() > height){
                    height = getChildAt(i).getMeasuredHeight();
                }
            }
            setMeasuredDimension(width,height);
        }

        //宽度是AT_MOST,则宽度为所有子元素的宽度和
        else if(widthMode == MeasureSpec.AT_MOST){
            int width = 0;
            for(int i = 0; i < getChildCount(); i++){
                width += getChildAt(i).getMeasuredWidth();
            }
            setMeasuredDimension(width,heightSize);
        }
        //高度是AT_MOST,则高度为所有子元素中最大的
        else if(heightMode == MeasureSpec.AT_MOST){
            int height = 0;
            for(int i = 0; i < getChildCount(); i++){
                if(height < getChildAt(i).getMeasuredHeight()){
                    height = getChildAt(i).getMeasuredHeight();
                }
            }
            setMeasuredDimension(widthSize,height);
        }
    }


}
