package com.cyl.wifi;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.cyl.wifi.utils.NetWorkUtils;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;
import com.lzb.lzbutils.utils.LogUtils;

public class ActivityNetwork extends AppCompatActivity  implements ConnectionClassManager.ConnectionClassStateChangeListener {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_network);
        NetWorkUtils.startSampling();
        NetWorkUtils.stopSampling();
        LogUtils.loge("TAG", NetWorkUtils.getCurrentBandwidthQuality());
        LogUtils.loge("TAG", NetWorkUtils.getDownloadKBitsPerSecond());
        DeviceBandwidthSampler.getInstance().startSampling();
        findViewById(R.id.btn_stop_network).setOnClickListener(view -> {
            DeviceBandwidthSampler.getInstance().startSampling();
            ConnectionQuality cq = ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
            LogUtils.loge("TAGA",cq);
            ConnectionClassManager.getInstance().addBandwidth(100*1024l, 10l);

        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        ConnectionClassManager.getInstance().register(this);

    }


    @Override
    public void onBandwidthStateChange(ConnectionQuality bandwidthState) {
            LogUtils.loge("TAGA",bandwidthState);
            LogUtils.loge("TAGA",bandwidthState.name());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        LogUtils.loge("TAGA",hasCapture);
    }
}