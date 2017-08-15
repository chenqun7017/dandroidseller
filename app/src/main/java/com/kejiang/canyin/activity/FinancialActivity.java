package com.kejiang.canyin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.adapter.FinancialAdapter;
import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenqun on 2017/7/25.
 * 首页—财务管理界面
 */

public class FinancialActivity extends BaseActivity {
    //头部控件
    @BindView(R.id.rl_back)
    public RelativeLayout rl_back;
    @BindViews({R.id.tv_title, R.id.tv_end})
    public List<TextView> textViewList;

    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }

    //流水收入  流水提现
    @BindViews({R.id.tv_income, R.id.tv_withdrawal})
    public List<TextView> twithDrawalList;
    @OnClick(R.id.tv_income)
    public void income(){
        showToast("收入流水");

    }
    @OnClick(R.id.tv_withdrawal)
    public void withdrawal(){
        showToast("提现流水");
    }

    @BindView(R.id.btn_withdrawa)
    public ImageButton btn_withdrawa;
    @OnClick(R.id.btn_withdrawa)
    public void btn(){
        startActivity(WithdrawActivity.class);
    }

    @BindView(R.id.rl_order)
    public ListView rl_order;

    private FinancialAdapter financialAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        ButterKnife.bind(this);
        initViews();

    }

    @Override
    protected void initViews() {
        if (rl_back.getVisibility() != View.VISIBLE) {
            rl_back.setVisibility(View.VISIBLE);

        }
        if (textViewList.get(0).getVisibility() != View.VISIBLE) {
            textViewList.get(0).setVisibility(View.VISIBLE);
            textViewList.get(0).setText(getResources().getString(R.string.financial));
        }
        if (textViewList.get(1).getVisibility() != View.GONE) {
            textViewList.get(1).setVisibility(View.GONE);
        }
        financialAdapter = new FinancialAdapter(this);
        rl_order.setAdapter(financialAdapter);

    }

}
