package com.cyl.wifi.baphuo.nativ;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import androidx.annotation.Nullable;

import com.lzb.mylibrary.baohuo.Native;

import java.util.Timer;
import java.util.TimerTask;

public class NativeService extends Service {
    private static final String TAG = "BAO";
    private int i = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        Native watcher = new Native();
        watcher.watcher(String.valueOf(Process.myUid()),  Process.myPid());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "服务进程，运行中 i = "+i);
                i++;
            }
        }, 0,  3000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
