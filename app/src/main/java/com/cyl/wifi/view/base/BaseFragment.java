package com.cyl.wifi.view.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by hch on 2018/4/9 0009.
 */
public abstract class BaseFragment extends RxFragment {
	public Activity mActivity;
	private int loading = 0;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView();
		initData();
	}

	public void initView() {

	}

	public void initData() {

	}

	public void refresh() {

	}






	/**
	 * 绑定生命周期
	 */
	public <T> LifecycleTransformer<T> bindToLife() {
		return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
	}


}
