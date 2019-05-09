package com.example.myapplication;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.RxJava.RxJavaStudyActivity;
import com.example.myapplication.amap.AMapTestActivity1;

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

    }
}
