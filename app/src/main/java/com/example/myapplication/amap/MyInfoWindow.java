package com.example.myapplication.amap;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.myapplication.R;
import com.example.myapplication.databinding.LayoutLocationInfoWindowBinding;

/**
 * description：
 * author：yiwen
 * date：2019/4/25 下午 2:59
 * remark：
 */
public class MyInfoWindow implements AMap.InfoWindowAdapter {
    private Context mContext;
    private ClickRefreshLocationListener refreshLocationListener;
    private LayoutLocationInfoWindowBinding binding;

    public MyInfoWindow(Context context){
        this.mContext = context;
    }

    public MyInfoWindow(Context context,ClickRefreshLocationListener refreshLocationListener){
        this(context);
        this.refreshLocationListener = refreshLocationListener;
    }

    public void setRefreshLocationListener(ClickRefreshLocationListener listener){
        this.refreshLocationListener = listener;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.layout_location_info_window,null,false);
        binding.setInfo(marker.getOptions());
        binding.tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(refreshLocationListener != null){
                    refreshLocationListener.refreshLocation();
                }
            }
        });
        return binding.getRoot();
    }

    //更新数据
    public void updateData(MarkerOptions options){
        if(binding != null){
            binding.setInfo(options);
        }
    }

    //定义接口，当点击刷新时更新位置信息
    public interface ClickRefreshLocationListener{
        void refreshLocation();
    }

}
