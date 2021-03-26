package com.cyl.wifi.ad.tt;

import android.app.Activity;
import android.view.View;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTBannerAd;

/**
 * 开屏广告
 */

public class SplashUtils {
    /**
     * 887409812
     * 开屏广告
     * @param activity
     */
    public void showAdSlot(Activity activity,String codeId){
        TTAdNative mTTAdNative = TTAdSdk.getAdManager().createAdNative(activity);
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(codeId)
                .setImageAcceptedSize(1080, 1920)
                .build();
    }

}


