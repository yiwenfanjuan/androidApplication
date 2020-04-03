package com.example.myapplication.amap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.project.baselib.ui.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityAmapTest1Binding;
import java.util.concurrent.TimeUnit;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AMapTestActivity1 extends BaseActivity<ActivityAmapTest1Binding> implements AMap.OnMyLocationChangeListener, MyInfoWindow.ClickRefreshLocationListener {

    private AMap mAMap;
    //UI对象
    private UiSettings mUiSettings;

    //需要在地图上进行标注的点
    private double mMarkX;
    private double mMarkY;
    private Marker mMarker;

    private MyInfoWindow myInfoWindow;

    //手指滑动的距离
    private float mPointY;
    private int mOldMarginTop;

    //最底部的View
    private View mBottomView;
    //最后一个View在屏幕上的位置
    int[] mScreenLocation;

    //屏幕信息
    private DisplayMetrics dm = new DisplayMetrics();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding.mapView.onCreate(savedInstanceState);
        if (mAMap == null) {
            mAMap = mBinding.mapView.getMap();
        }
        mAMap.setOnMyLocationChangeListener(this);
        mUiSettings = mAMap.getUiSettings();
        //mAMap.setLocationSource(this);
        mUiSettings.setMyLocationButtonEnabled(true);
        mAMap.setMyLocationEnabled(true);

        myInfoWindow = new MyInfoWindow(this, this);
        mAMap.setInfoWindowAdapter(myInfoWindow);
        //设置地图缩放等级为13，共有3-17这15个等级
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(13));
        //设置默认使用夜景地图
        mAMap.setMapType(AMap.MAP_TYPE_NIGHT);
        setLocationStyle();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBinding.mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBinding.mapView.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_amap_test1;
    }

    @Override
    protected void initUi() {
        //设置图片的marginTop为屏幕的2/3
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mBinding.clContent.getLayoutParams();
        params.topMargin = dm.heightPixels * 2 / 3;
        mBinding.clContent.setLayoutParams(params);

        //监听任务信息的滑动状态
        mBinding.clContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPointY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mBinding.clContent.getLayoutParams();
                        params.topMargin = (int) (params.topMargin + (event.getRawY() - mPointY));
                        //最大不能大于屏幕的2/3
                        if (params.topMargin > dm.heightPixels * 2 / 3) {
                            params.topMargin = dm.heightPixels * 2 / 3;
                        }

                        if(mBottomView == null) {
                            mBottomView = mBinding.tvTaskFinish.getVisibility() == View.GONE ? mBinding.viewTaskInfo : mBinding.tvTaskFinish;
                        }
                        if(mScreenLocation == null) {
                            mScreenLocation = new int[2];
                        }
                        mBottomView.getLocationOnScreen(mScreenLocation);
                       //当最后一个View显示在屏幕上的时候不再进行滑动
                        if(event.getRawY() - mPointY < 0) {
                            if (mScreenLocation[1] + mBottomView.getHeight() < dm.heightPixels - 100) {
                                if(params.topMargin <= 0) {
                                    params.topMargin = mOldMarginTop;
                                }
                            }
                        }



                        mBinding.clContent.setLayoutParams(params);
                        mOldMarginTop = params.topMargin;
                        mPointY = event.getRawY();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initData() {

    }

    //显示定位蓝点：
    private void setLocationStyle() {
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.showMyLocation(true);
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效
        locationStyle.interval(2000);
        //设置连续定位，且将视角移动到地图中心点，定位蓝点跟随设备移动
        locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //定位蓝点的图标自定义
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        locationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
        //设置精度圈
        locationStyle.strokeColor(Color.TRANSPARENT);
        locationStyle.radiusFillColor(Color.TRANSPARENT);
        locationStyle.strokeWidth(0f);
        mAMap.setMyLocationStyle(locationStyle);
        mAMap.setMyLocationEnabled(true);
    }

    @Override
    public void onMyLocationChange(Location location) {
        Log.i(TAG, "定位信息：" + location.toString());
        //获取到定位信息
        mMarkX = location.getLatitude() - 0.030;
        mMarkY = location.getLongitude() - 0.030;
        if (mMarker == null) {
            LatLng latLng = new LatLng(mMarkX, mMarkY, true);
            mMarker = mAMap.addMarker(new MarkerOptions().position(latLng).title("客户当前位置").snippet("userPosition").visible(true).setFlat(true));
            mMarker.showInfoWindow();
        }
    }


    @Override
    public void refreshLocation() {
        //模拟请求数据
        Single.<Object>timer(3, TimeUnit.SECONDS, Schedulers.io())
                .toFlowable()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, "数据请求成功");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (myInfoWindow != null) {
                                    myInfoWindow.updateData(new MarkerOptions().title(String.valueOf(aLong)));
                                }
                            }
                        });

                    }
                });

    }
}
