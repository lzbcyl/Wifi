package com.cyl.wifi.baphuo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cyl.wifi.utils.ServiceUtils;
import com.lzb.lzbutils.utils.LogUtils;

public class MyBroadcoastReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.loge("Tag","222232");
        if (!ServiceUtils.isServiceRunning(context.getApplicationContext(), BroadCostReceiverService.class)) {
            LogUtils.loge("Tag", "检测到服务未在运行,启动服务");
            Intent serviceIntent = new Intent(context, BroadCostReceiverService.class);
            context.startService(serviceIntent);
        } else {
            LogUtils.loge("Tag", "检测到服务正在运行,启动服务");
        }

    }
}
