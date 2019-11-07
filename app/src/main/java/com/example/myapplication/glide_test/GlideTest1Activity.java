package com.example.myapplication.glide_test;

import android.Manifest;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityGlideTest1Binding;
import com.example.myapplication.glide_test.test_life_cycle.PermissionResultListener;
import com.example.myapplication.glide_test.test_life_cycle.PermissionUtils;
import com.example.myapplication.glide_test.test_life_cycle.TestAttachLifeCycleFragment;
import com.example.myapplication.glide_test.test_life_cycle.TestComponentCallBack;

import java.util.List;

public class GlideTest1Activity extends BaseActivity<ActivityGlideTest1Binding> implements PermissionResultListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide_test1;
    }

    @Override
    protected void initUi() {
        RequestOptions options = new RequestOptions()
                .onlyRetrieveFromCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        ViewTarget target = Glide.with(this)
                .load("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=92afee66fd36afc3110c39658318eb85/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg")
                .into(mBinding.image);



        getBaseContext().getApplicationContext().registerComponentCallbacks(new TestComponentCallBack());

        PermissionUtils permissionUtils = PermissionUtils.getInstance();
        permissionUtils.setPermissionResultListener(this);
        permissionUtils.with(getSupportFragmentManager(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE});

    }

    @Override
    protected void initData() {

    }

    @Override
    public void requestPermissionSuccess() {
        Toast.makeText(this, "权限请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestPermissionFailed(List<String> failedPermissions) {
        Log.i(TAG, "以下权限拒绝授予");
        for (String permission : failedPermissions) {
            Log.i(TAG, permission);
        }
    }

    @Override
    public void requestPermissionFailed(String errorMessage) {
        Toast.makeText(this, "权限请求失败", Toast.LENGTH_SHORT).show();
    }
}
