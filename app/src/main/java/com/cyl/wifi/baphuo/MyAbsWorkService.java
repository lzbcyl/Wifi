package com.cyl.wifi.baphuo;

import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cyl.wifi.baphuo.receiver.BroadCostReceiverService;
import com.cyl.wifi.baphuo.receiver.MyBroadcoastReceiver;
import com.lzb.lzbutils.utils.LogUtils;
import com.xdandroid.hellodaemon.AbsWorkService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MyAbsWorkService extends AbsWorkService {
    public static Disposable sDisposable;

    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        LogUtils.loge("TAG","--------shouldStopService---");
        return null;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        LogUtils.loge("TAG","--------startWork---");
        sDisposable = Observable
                .interval(3, TimeUnit.SECONDS)
                //取消任务时取消定时唤醒
                .doOnDispose(() -> {
                    System.out.println("保存数据到磁盘。");
                    cancelJobAlarmSub();
                })
                .subscribe(count -> {
                    System.out.println("每 3 秒采集一次数据... count = " + count);
                    if (count > 0 && count % 18 == 0) System.out.println("保存数据到磁盘。 saveCount = " + (count / 18 - 1));
                });
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        LogUtils.loge("TAG","--------stopWork---");

    }

    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        LogUtils.loge("TAG","--------isWorkRunning---");

        return null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent, Void alwaysNull) {
        LogUtils.loge("TAG","--------onBind---");
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {
        LogUtils.loge("TAG","--------onServiceKilled--1-");
        Toast.makeText(this,"1",Toast.LENGTH_LONG).show();
        Intent intent1 = new Intent(this, MyBroadcoastReceiver.class);
        sendBroadcast(intent1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show();
        LogUtils.loge("TAG","--------onDestroy---");
    }
}
