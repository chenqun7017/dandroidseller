package com.kejiang.canyin.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;


/**
 * Created by chenqun on 2017/7/24.
 * 消息
 */

public class MessageFragment extends BaseFragment {

	//头部控件
	@BindView( R.id.rl_back )
	public RelativeLayout rl_back;
	@BindViews( {R.id.tv_title, R.id.tv_end} )
	public List<TextView> textViewList;

	View view;

	@Override
	public View initView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.tab_message_fragment,null);
		ButterKnife.bind(this, view);
		initViewEvent();
		return view;
	}

	private void initViewEvent() {
		if (rl_back.getVisibility()!=View.GONE){
			rl_back.setVisibility(View.GONE);
		}
		if (textViewList.get(0).getVisibility()!=View.VISIBLE){
			textViewList.get(0).setVisibility(View.VISIBLE);
			textViewList.get(0).setText(getResources().getString(R.string.bottom_tab_message));
		}
		if (textViewList.get(1).getVisibility()!=View.GONE){
			textViewList.get(1).setVisibility(View.GONE);
		}

	}

}
