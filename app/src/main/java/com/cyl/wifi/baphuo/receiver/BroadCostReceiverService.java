package com.cyl.wifi.baphuo.receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cyl.wifi.utils.SharedPreferenceTool;
import com.lzb.lzbutils.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

public class BroadCostReceiverService extends Service {
    private Timer timer;
    private TimerTask timerTask;
    public static int count;

    @Override
    public void onCreate() {
       LogUtils.loge("TAG", "服务创建了");
        int save = SharedPreferenceTool.getInstance(getApplicationContext()).getInt("count", -1);
        if (save == -1) {
            this.count = 0;
           LogUtils.loge("TAG", "count首次启动，从0开始计数");
        } else {
            this.count = save;
           LogUtils.loge("TAG", "count从上次保存的 " + save + " 开始计数");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTask();
       LogUtils.loge("TAG", "服务启动了");

        return START_STICKY;
    }

    /**
     * 开启定时任务，count每秒+1
     */
    private void startTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
               LogUtils.loge("TAG", "服务运行中，count: " + count);
                count++;
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * 结束定时任务
     */
    private void stopTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        count = 0;
    }

    @Override
    public void onDestroy() {
        stopTask();
       LogUtils.loge("TAG", "服务停止了");

        Intent intent = new Intent(this, BroadCostReceiverService.class);
        sendBroadcast(intent);
       LogUtils.loge("TAG", "发送保活广播");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
