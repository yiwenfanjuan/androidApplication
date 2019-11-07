package com.example.myapplication.material_design_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.R;

/**
 * @author zyf
 * @version 1.0
 * @intro MaterialDesign风格控件首页
 * @date 2019/11/5
 * @descrption
 */
public class MaterialDesignDemoHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_demo_home);

        findViewById(R.id.btn_footer_behavior).setOnClickListener((v) ->
                startActivity(new Intent(this, FooterBehaviorDemoActivity.class))
        );
    }
}
