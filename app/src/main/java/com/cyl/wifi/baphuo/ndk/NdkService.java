package com.cyl.wifi.baphuo.ndk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import androidx.annotation.Nullable;

import com.lzb.lzbutils.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

public class NdkService extends Service {
    public final static String TAG = "TAG";
    int i=0;

    static {
        System.loadLibrary("native-lib");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.loge("TAG","a");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.loge("TAG","a");
        Wathcer wathcer = new Wathcer();
        wathcer.startDaemon(Process.myUid());
        Timer timer = new Timer();
        //定时器
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        Log.i(TAG, "服务  " + i);
                        i++;
                    }
                }, 0, 1000 * 3);
    }
}
