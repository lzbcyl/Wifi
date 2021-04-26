package com.lzb.mylibrary.baohuo;

public class Native {
    static {
        try {
            System.loadLibrary("leoric");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public native void watcher(String userId, int processId);

}
