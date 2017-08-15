package com.kejiang.canyin.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.kejiang.canyin.activity.AestaurantMagActivity;
import com.kejiang.canyin.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenqun on 2017/7/24.
 * 我的
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.rl_mag)
    public RelativeLayout rl_mag;

    @OnClick(R.id.rl_mag)
    public void mag() {
        startActivity(AestaurantMagActivity.class);
    }

    View rootView;

    @Override
    public View initView(LayoutInflater inflater) {
        rootView = inflater.inflate(R.layout.tab_me_fragment, null);
        ButterKnife.bind(this, rootView);
        initViewEvent();
        return rootView;
    }

    private void initViewEvent() {

    }

}
