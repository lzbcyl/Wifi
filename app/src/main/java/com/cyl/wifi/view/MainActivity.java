package com.cyl.wifi.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.cyl.wifi.R;
import com.cyl.wifi.ad.tt.SplashActivity;
import com.cyl.wifi.baphuo.leoric.Service1;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.splash).setOnClickListener(v->{
            Intent intent=new Intent(this, SplashActivity.class);
            this.startActivity(intent);

        });
        findViewById(R.id.network).setOnClickListener(v->{
            Intent intent=new Intent(this, ActivityNetwork.class);
            this.startActivity(intent);

        });
        CallUtils.callSimpleInfo();

        findViewById(R.id.main2).setOnClickListener(v->{
            Intent intent=new Intent(this, MainActivity2.class);
            this.startActivity(intent);
        });

        startService(new Intent(MainActivity.this, Service1.class));


    }
}