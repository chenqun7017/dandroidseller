package com.kejiang.canyin.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.kejiang.canyin.fragment.R;
import com.kejiang.canyin.view.AbstractSpinerAdapter.IOnItemSelectListener;

import java.util.List;

/**
 * Created by chenqun on 2017/8/1.
 * 自定义网页类型spiner
 */

public class SpinerPopWindow extends PopupWindow implements OnItemClickListener{

	private Context mContext;
	private ListView mListView;
	private TextView tv_tt;
	private NormalSpinerAdapter mAdapter;
	private IOnItemSelectListener mItemSelectListener;
	
	
	public SpinerPopWindow(Context context)
	{
		super(context);
		
		mContext = context;
		init();
	}
	
	
	public void setItemListener(IOnItemSelectListener listener){
		mItemSelectListener = listener;
	}

	
	private void init()
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.spiner_window_layout, null);
		setContentView(view);		
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		
		setFocusable(true);
    	ColorDrawable dw = new ColorDrawable(0x00);
		setBackgroundDrawable(dw);
	
		
		mListView = (ListView) view.findViewById(R.id.listview);
		tv_tt = (TextView) view.findViewById(R.id.tv_tt);
		tv_tt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				Toast.makeText(mContext,"55",Toast.LENGTH_SHORT).show();
			}
		});

		mAdapter = new NormalSpinerAdapter(mContext);	
		mListView.setAdapter(mAdapter);	
		mListView.setOnItemClickListener(this);
	}
	
	
	public void refreshData(List<String> list, int selIndex)
	{
		if (list != null && selIndex  != -1)
		{
			mAdapter.refreshData(list, selIndex);
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		dismiss();
		if (mItemSelectListener != null){
			mItemSelectListener.onItemClick(pos);
		}
	}


}
