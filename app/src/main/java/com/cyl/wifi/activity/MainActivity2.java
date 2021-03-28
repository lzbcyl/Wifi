package com.cyl.wifi.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cyl.wifi.R;
import com.cyl.wifi.databinding.ActivityMain2BindingImpl;
import com.cyl.wifi.model.Main2ViewModel;
import com.cyl.wifi.utils.ActvityUtils;
import com.lzb.lzbutils.base.BaseLoadActivity;

public class MainActivity2 extends BaseLoadActivity<Main2ViewModel, ActivityMain2BindingImpl> {
    @Override
    public int initView() {
        return R.layout.activity_main2;
    }

    @Override
    public void init() {
        ActvityUtils.showStatusBg(this);
        showContentView();
    }
}
