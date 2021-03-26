package com.cyl.wifi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bytedance.sdk.openadsdk.TTAdManager;
import com.cyl.wifi.ad.tt.SplashActivity;
import com.cyl.wifi.baphuo.JobHandlerService;
import com.cyl.wifi.utils.NetWorkUtils;

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



    }
}