package com.kejiang.canyin.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.fragment.R;

public class MenuLeftAdapter extends BaseAdapter {
	Context context;
	String navig[];
	int images[];
	int selectPositon=-1;
	public MenuLeftAdapter(Context context, String navig[], int images[]) {
		this.context=context;
		this.navig=navig;
		this.images=images;
	}
	@Override
	public int getCount() {
		return navig.length;
	}

	@Override
	public Object getItem(int arg0) {
		return navig[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder view;
		if(arg1==null)
		{
			view=new ViewHolder();
			arg1=LayoutInflater.from(context).inflate(R.layout.leftitem, null);
			view.imageView=(ImageView) arg1.findViewById(R.id.iv_menu_hot);
			view.textView=(TextView)arg1.findViewById(R.id.tv_menu_name);
			view.layout=(RelativeLayout)arg1.findViewById(R.id.leftlayout);
			arg1.setTag(view);
		}
		else
		{
			view=(ViewHolder)arg1.getTag();
		}
		/*
		 * 设置选中效果
		 */
		if(selectPositon==arg0)
		{
			view.textView.setTextColor(Color.CYAN);
			//view.layout.setBackgroundColor(Color.DKGRAY);
		}
		else
		{
			view.textView.setTextColor(Color.BLACK);
			//view.layout.setBackgroundColor(Color.TRANSPARENT); //设为透明
		}
		if (arg0<=1){
			view.imageView.setImageResource(images[arg0]);
		}
		view.textView.setText(navig[arg0]);
		return arg1;
	}
	class ViewHolder
	{
		ImageView imageView;
		TextView textView;
        RelativeLayout layout;
	}
	public void setSelectedPosition(int positon)
	{
		selectPositon=positon;
	}

}
