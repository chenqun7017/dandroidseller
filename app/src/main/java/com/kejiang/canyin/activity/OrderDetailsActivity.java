package com.kejiang.canyin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.adapter.OderDetailAdapter;
import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenqun on 2017/7/26.
 *
 */

public class OrderDetailsActivity extends BaseActivity {

    //头部控件
    @BindView(R.id.rl_back)
    public RelativeLayout rl_back;
    @BindViews({R.id.tv_title, R.id.tv_end})
    public List<TextView> textViewList;

    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }

    @BindView(R.id.lv_order)
    public ListView lv_order;

    private OderDetailAdapter  oderDetailAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        ButterKnife.bind(this);
        initViews();

    }

    @Override
    protected void initViews() {

        if (rl_back.getVisibility()!= View.VISIBLE){
            rl_back.setVisibility(View.VISIBLE);
        }
        if (textViewList.get(0).getVisibility()!=View.VISIBLE){
            textViewList.get(0).setVisibility(View.VISIBLE);
            textViewList.get(0).setText(getResources().getString(R.string.orderdetails));
        }
        if (textViewList.get(1).getVisibility()!=View.GONE){
            textViewList.get(1).setVisibility(View.GONE);
        }
        oderDetailAdapter=new OderDetailAdapter(this);
        lv_order.setAdapter(oderDetailAdapter);


    }

}