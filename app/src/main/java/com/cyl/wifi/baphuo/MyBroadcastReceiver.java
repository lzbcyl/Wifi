package com.cyl.wifi.baphuo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lzb.lzbutils.utils.LogUtils;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.loge("TAG","-------{}--->"+intent);
    }
}
