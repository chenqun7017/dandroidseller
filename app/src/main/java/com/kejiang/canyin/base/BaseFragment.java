package com.kejiang.canyin.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kejiang.canyin.fragment.R;

import com.kejiang.canyin.dialog.ProgressBarDialog;

/**
 * Created by chenqun on 2017/7/24.
 * 所有Fragment的基类
 */

public abstract class BaseFragment extends Fragment {
	public View view;
	public Context mContext;
	public static ProgressBarDialog mprogress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mprogress=new ProgressBarDialog();
		mContext = getActivity();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = initView(inflater);
		/**防止次FragmentTabHost切换fragment时会调用onCreateView()重绘UI */
		/*if (view == null) {
			view = initView(inflater);
		}*/
		 /**
		  * 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，
		  * 要不然会发生这个rootview已经有parent的错误。 
		  */
		/* ViewGroup parent = (ViewGroup) view.getParent();
		    if (parent != null){  
		        parent.removeView(view);  
		    }*/
		return view;
	}
	/** 初始化view */
	public abstract View initView(LayoutInflater inflater);

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(mContext, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}
	
	/** 显示自定义Toast提示 **/
	protected void showCustomToast(String text) {
		View toastRoot = LayoutInflater.from(mContext).inflate(
				R.layout.basetoast, null);
		((TextView) toastRoot.findViewById(R.id.tv_toast)).setText(text);
		Toast toast = new Toast(mContext);
		toast.setView(toastRoot);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}
}
