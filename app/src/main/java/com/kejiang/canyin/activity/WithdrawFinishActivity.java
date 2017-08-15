package com.kejiang.canyin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenqun on 2017/7/31.
 */

public class WithdrawFinishActivity extends BaseActivity{
    //头部控件
    @BindView( R.id.rl_back )
    public RelativeLayout rl_back;
    @BindViews( {R.id.tv_title, R.id.tv_end} )
    public List<TextView> textViewList;

    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawfinish);
        ButterKnife.bind( this );
        initViews();
    }

    @Override
    protected void initViews() {
        if (rl_back.getVisibility()!= View.VISIBLE){
            rl_back.setVisibility(View.VISIBLE);
        }
        if (textViewList.get(0).getVisibility()!=View.VISIBLE){
            textViewList.get(0).setVisibility(View.VISIBLE);
            textViewList.get(0).setText(getResources().getString(R.string.withdraw_finish));
        }
        if (textViewList.get(1).getVisibility()!=View.GONE){
            textViewList.get(1).setVisibility(View.GONE);
        }

    }

}
