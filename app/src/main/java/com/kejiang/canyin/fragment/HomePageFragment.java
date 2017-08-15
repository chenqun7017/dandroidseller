package com.kejiang.canyin.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.activity.FinancialActivity;
import com.kejiang.canyin.activity.MenuActivity;
import com.kejiang.canyin.activity.OrderActivity;
import com.kejiang.canyin.activity.SaleSetActivity;
import com.kejiang.canyin.activity.TrafficActivity;
import com.kejiang.canyin.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kejiang.canyin.fragment.R.id.ll_financial;
import static com.kejiang.canyin.fragment.R.id.ll_menu;
import static com.kejiang.canyin.fragment.R.id.ll_order;
import static com.kejiang.canyin.fragment.R.id.ll_traffic;

/**
 * Created by chenqun on 2017/7/24.
 * 首页
 */

public class HomePageFragment extends BaseFragment{

    //根布局
    View rootView;
    //行业动态列表
    @BindView(R.id.lv_listview)
    public ListView lv_listview;

    @BindView(R.id.tv_shop)
    public TextView tv_shop;

    @BindView(R.id.tv_address)
    public TextView tv_address;

    @BindView(R.id.im_shop)
    public ImageView im_shop;


    //管理菜单根布局
    @BindViews({ll_menu, ll_order, ll_traffic, ll_financial})
    public List<LinearLayout> linearLayoutList;
    @OnClick(R.id.ll_menu)
    public void menu(){
        startActivity( MenuActivity.class);
    }
    @OnClick(R.id.ll_order)
    public void order(){
        startActivity( OrderActivity.class);
    }
    @OnClick(R.id.ll_traffic)
    public void traffic(){
        startActivity( TrafficActivity.class);
    }
    @OnClick(R.id.ll_financial)
    public void financial(){
        startActivity( FinancialActivity.class);
    }


    @BindView(R.id.rl_set)
    public RelativeLayout rl_set;
    @OnClick(R.id.rl_set)
    public void set(){
        startActivity( SaleSetActivity.class);
    }

    @Override
    public View initView(LayoutInflater inflater) {
        rootView = inflater.inflate(R.layout.tab_homepage_fragment, null);
        ButterKnife.bind( this , rootView ) ;
        findView();
        return rootView;
    }

    private void findView() {
        lv_listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_fg_homepage, null);
                return convertView;
            }
        });

    }


}
