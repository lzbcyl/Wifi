package com.cyl.wifi.utils;

import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;

public class NetWorkUtils {
    private static final String TAG = "NetSpeedUtil";

    /**
     * 开始采样(添加到网络请求开始前)
     */
    public static void startSampling() {
        DeviceBandwidthSampler.getInstance().startSampling();
    }

    /**
     * 结束采样(添加到网络请求结束后)
     */
    public static void stopSampling() {
        // Do some downloading tasks
        DeviceBandwidthSampler.getInstance().stopSampling();
    }

    /**
     * 当前网络质量
     * <p>
     * <p>
     * POOR: Bandwidth under 150 kbps.
     * MODERATE: Bandwidth between 150 and 550 kbps.
     * GOOD: Bandwidth between 550 and 2000 kbps.
     * EXCELLENT: Bandwidth over 2000 kbps.
     * UNKNOWN: Placeholder for unknown bandwidth. This is the initial value and will stay at this value if a bandwidth cannot be accurately found.
     */
    public static String getCurrentBandwidthQuality() {
        ConnectionQuality currQuality = ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
        if (currQuality != null) {
            return currQuality.toString();
        }
        return "unknown";
    }


    /**
     * 当前网络质量
     */
    public static double getDownloadKBitsPerSecond() {
        double bitsPerSecond = ConnectionClassManager.getInstance().getDownloadKBitsPerSecond();
        return bitsPerSecond;
    }
}
