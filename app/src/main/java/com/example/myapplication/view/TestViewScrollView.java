package com.example.myapplication.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
*  @intro 测试滑动的View
*  @author zyf
*  @date 2019/11/7
*  @descrption
*  @version 1.0
*/
public class TestViewScrollView extends View {

    private static final String TAG = TestViewScrollView.class.getSimpleName();

    //手指上次的位置
    private int lastX,lastY;

    //定义Scroller
    private Scroller mScroller;

    public TestViewScrollView(Context context) {
        this(context,null);
    }

    public TestViewScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestViewScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScroller();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestViewScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    //初始化Scroller
    private void initScroller(){
        mScroller = new Scroller(getContext());
    }

    //计算滑动距离
    @Override
    public void computeScroll() {
        super.computeScroll();
        Log.e(TAG,"computeScroll:"+ getScrollX());
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    //一个缓慢滑动地方法
    public void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        int deltaY = destY - getScrollY();
        mScroller.startScroll(scrollX,0,deltaX,deltaY,2000);
        invalidate();
    }

    //重写onTouchEvent()方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手指当前所在的位置
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //如果是按下事件，就将当前的点击位置作为上一次的点击位置
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //如果是移动事件，就计算和按下事件的偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //计算出偏移量之后调用相应的方法对View的位置进行更新

                /*
                //使用layout()方法更新View的位置信息
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                        *
                 */

                /*
                //使用offsetLeftAndRight()和offsetTopAndBottom()更新位置信息
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                *
                 */
                /*
                //使用LayoutParams方式更新位置信息
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
                params.topMargin = getTop() + offsetY;
                params.leftMargin = getLeft() + offsetX;
                setLayoutParams(params);

                scrollTo(0,0);
                *
                 */

                //使用scrollTo/ScrollBy移动View
                //((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
        }
        return true;

    }
}
