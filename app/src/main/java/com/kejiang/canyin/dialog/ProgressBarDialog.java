package com.kejiang.canyin.dialog;

import android.view.LayoutInflater;
import android.view.View;

import com.kejiang.canyin.base.BaseDialog;
import com.kejiang.canyin.fragment.R;

/**
 * Created by chenqun on 2017/8/1.
 */

public class ProgressBarDialog extends BaseDialog {
	private View viewRoot;

	@Override
	public View initView(LayoutInflater inflater) {
		viewRoot = inflater.inflate(R.layout.dailog_progressbar, null);
		return viewRoot;
	}
	
	 @Override
	public void onResume() {
		super.onResume();
	}
}
