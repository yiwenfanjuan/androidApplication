package com.example.myapplication;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.RxJava.RxJavaStudyActivity;
import com.example.myapplication.amap.AMapTestActivity1;
import com.example.myapplication.drawable.DrawableTest1Activity;
import com.example.myapplication.glide_test.GlideStudyActivity;
import com.example.myapplication.material_design_demo.MaterialDesignDemoHomeActivity;
import com.example.myapplication.view.CustomViewTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_scroller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScrollLayoutTestActivity.class));
            }
        });

        findViewById(R.id.btn_rxJava).setOnClickListener((view) ->{
            startActivity(new Intent(MainActivity.this,RxJavaStudyActivity.class));
        });

        findViewById(R.id.btn_amap).setOnClickListener((view)->{
            startActivity(new Intent(MainActivity.this, AMapTestActivity1.class));
        });

        findViewById(R.id.btn_drawable).setOnClickListener((view) -> {
            startActivity(new Intent(MainActivity.this, DrawableTest1Activity.class));
        });

        findViewById(R.id.btn_glide).setOnClickListener((view)-> startActivity(new Intent(MainActivity.this, GlideStudyActivity.class)));


        findViewById(R.id.btn_material_design).setOnClickListener((view)-> startActivity(new Intent(MainActivity.this, MaterialDesignDemoHomeActivity.class)));

        findViewById(R.id.btn_custom_view).setOnClickListener((v) -> startActivity(new Intent(this, CustomViewTestActivity.class)));
    }
}
