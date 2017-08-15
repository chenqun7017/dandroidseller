package com.kejiang.canyin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.OrderAllFragment;
import com.kejiang.canyin.fragment.OrderFinishFragment;
import com.kejiang.canyin.fragment.OrderUnfinishedFragment;
import com.kejiang.canyin.fragment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kejiang.canyin.fragment.R.id.tv_taba;
import static com.kejiang.canyin.fragment.R.id.tv_tabb;
import static com.kejiang.canyin.fragment.R.id.tv_tabc;

/**
 * Created by chenqun on 2017/7/24.
 */

public class OrderActivity extends BaseActivity {
    //头部控件
    @BindView(R.id.rl_back)
    public RelativeLayout rl_back;
    @BindViews({R.id.tv_title, R.id.tv_end})
    public List<TextView> textViewList;

    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }



    // 选项卡tab布局
    @BindViews({R.id.rl_s1, R.id.rl_s2, R.id.rl_s3})
    public List<RelativeLayout> relativeLayoutList;
    @OnClick(R.id.rl_s1)
    public void rl1(){
        clickTab1Layout();
    }
    @OnClick(R.id.rl_s2)
    public void rl2(){
        clickTab2Layout();
    }
    @OnClick(R.id.rl_s3)
    public void rl3(){
        clickTab3Layout();
    }

    // 底部标签切换的Fragment
    private Fragment orderAllFragment, orderFinishFragment, orderUnfinishedFragment,
            currentFragment;

    // 底部标签色
    @BindViews({tv_taba, tv_tabb, tv_tabc})
    public  List<TextView>  textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
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
            textViewList.get(0).setText(getResources().getString(R.string.ordermag));
        }
        if (textViewList.get(1).getVisibility() != View.VISIBLE) {
            textViewList.get(1).setVisibility(View.VISIBLE);
        }

        initTab();


    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (orderAllFragment == null) {
            orderAllFragment = new OrderAllFragment();
        }
        textViews.get(0).setVisibility(View.VISIBLE);
        textViews.get(1).setVisibility(View.GONE);
        textViews.get(2).setVisibility(View.GONE);

        if (!orderAllFragment.isAdded()) {// 如果当前fragment未被添加，则添加到Fragment管理器中
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, orderAllFragment).commit();

            // 记录当前Fragment
            currentFragment = orderAllFragment;
        }
    }

    /**
     * 点击第1个tab
     */
    private void clickTab1Layout() {
        if (orderAllFragment == null) {
            orderAllFragment = new OrderAllFragment();
        }
        textViews.get(0).setVisibility(View.VISIBLE);
        textViews.get(1).setVisibility(View.GONE);
        textViews.get(2).setVisibility(View.GONE);
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), orderAllFragment);

    }


    /**
     * 点击2个tab
     */
    private void clickTab2Layout() {

        if (orderFinishFragment == null) {
            orderFinishFragment = new OrderFinishFragment();
        }
        textViews.get(0).setVisibility(View.GONE);
        textViews.get(1).setVisibility(View.VISIBLE);
        textViews.get(2).setVisibility(View.GONE);
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), orderFinishFragment);

    }

    /**
     * 点击第3个tab
     */
    private void clickTab3Layout() {
        if (orderUnfinishedFragment == null) {
            orderUnfinishedFragment = new OrderUnfinishedFragment();
        }
        textViews.get(0).setVisibility(View.GONE);
        textViews.get(1).setVisibility(View.GONE);
        textViews.get(2).setVisibility(View.VISIBLE);


        addOrShowFragment(getSupportFragmentManager().beginTransaction(), orderUnfinishedFragment);

    }


    /**
     * 添加或者显示碎片
     * <p>
     * param transaction
     * param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) {
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }

}
