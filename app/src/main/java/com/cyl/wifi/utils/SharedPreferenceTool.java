package com.cyl.wifi.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceTool {
    public static SharedPreferenceTool instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPreferenceTool(Context context) {
        sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPreferenceTool getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreferenceTool.class) {
                if (instance == null) {
                    // 使用双重同步锁
                    instance = new SharedPreferenceTool(context);
                }
            }
        }
        return instance;
    }

    /**
     * 往SharedPreference存放整型数据
     */
    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 从SharedPreference取出整型数据
     */
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }
}
