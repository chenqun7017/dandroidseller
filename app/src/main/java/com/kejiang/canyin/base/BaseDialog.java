package com.kejiang.canyin.base;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

@SuppressLint("NewApi")
public abstract class BaseDialog extends DialogFragment {
	/** 官方推荐使用DialogFragment有生命周期可管理Dialog,不建议Dialog*/
	public View view;
	public Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new
				ColorDrawable(Color.TRANSPARENT));
		view = initView(inflater);
		return view;
	}
	

	/**初始化view*/
	public abstract View initView(LayoutInflater inflater);
}
