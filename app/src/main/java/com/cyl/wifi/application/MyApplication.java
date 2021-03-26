package com.cyl.wifi.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.cyl.wifi.MainActivity;
import com.cyl.wifi.R;
import com.cyl.wifi.ad.config.TTAdManagerHolder;
import com.cyl.wifi.baphuo.JobHandlerService;
import com.cyl.wifi.baphuo.MyAbsWorkService;
import com.fanjun.keeplive.KeepLive;
import com.fanjun.keeplive.config.ForegroundNotification;
import com.fanjun.keeplive.config.ForegroundNotificationClickListener;
import com.fanjun.keeplive.config.KeepLiveService;
import com.lzb.lzbutils.utils.LogUtils;
import com.qq.e.comm.managers.GDTADManager;
import com.xdandroid.hellodaemon.DaemonEnv;

public class MyApplication extends Application {
    public  static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        GDTADManager.getInstance().initWith(this, "您在腾讯联盟开发者平台的APPID");
        TTAdManagerHolder.init(this);
        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
        TTAdSdk.init(this,
                new TTAdConfig.Builder()
                        .appId("5001121")
                        .useTextureView(true) //默认使用SurfaceView播放视频广告,当有SurfaceView冲突的场景，可以使用TextureView
                        .appName("APP测试媒体")
                        .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)//落地页主题
                        .allowShowNotify(true) //是否允许sdk展示通知栏提示
                        .debug(true) //测试阶段打开，可以通过日志排查问题，上线时去除该调用
                        .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI) //允许直接下载的网络状态集合,没有设置的网络下点击下载apk会有二次确认弹窗，弹窗中会披露应用信息
                        .supportMultiProcess(false)//是否支持多进程，true支持
                        .asyncInit(true) //是否异步初始化sdk,设置为true可以减少SDK初始化耗时。3450版本开始废弃~~
                        //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
                        .build());

        DaemonEnv.initialize(this, MyAbsWorkService.class, 2);
        startService(new Intent(this, MyAbsWorkService.class));

        //如果明确某个进程不会使用到广告SDK，可以只针对特定进程初始化广告SDK的content
        //if (PROCESS_NAME_XXXX.equals(processName)) {
        //   TTAdSdk.init(context, config);
        //}
        Intent intent = new Intent();
        intent.setClass(this, JobHandlerService.class);
        startService(intent);


        //定义前台服务的默认样式。即标题、描述和图标
        ForegroundNotification foregroundNotification = new ForegroundNotification("测试","描述", R.mipmap.ic_launcher,
                //定义前台服务的通知点击事件
                new ForegroundNotificationClickListener() {

                    @Override
                    public void foregroundNotificationClick(Context context, Intent intent) {
                        LogUtils.loge("TAG","-+-foregroundNotificationClick-----");
                    }
                });
        //启动保活服务
        KeepLive.startWork(this, KeepLive.RunMode.ROGUE, foregroundNotification,
                //你需要保活的服务，如socket连接、定时任务等，建议不用匿名内部类的方式在这里写
                new KeepLiveService() {
                    /**
                     * 运行中
                     * 由于服务可能会多次自动启动，该方法可能重复调用
                     */
                    @Override
                    public void onWorking() {
                        Intent intent = new Intent();
                        intent.setClass(context, JobHandlerService.class);
                        startService(intent);
                        LogUtils.loge("TAG","-+-onWorking-----");
                    }
                    /**
                     * 服务终止
                     * 由于服务可能会被多次终止，该方法可能重复调用，需同onWorking配套使用，如注册和注销broadcast
                     */
                    @Override
                    public void onStop() {
                        Toast.makeText(context,"asdf",Toast.LENGTH_LONG).show();
                        LogUtils.loge("TAG","-+-onStop-----");
                    }
                }
        );
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.e("TAG", "onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.e("TAG", "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        Log.e("TAG", "onTrimMemory");
        super.onTrimMemory(level);
    }
}
