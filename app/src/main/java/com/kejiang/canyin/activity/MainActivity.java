package com.kejiang.canyin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.HomePageFragment;
import com.kejiang.canyin.fragment.MeFragment;
import com.kejiang.canyin.fragment.MessageFragment;
import com.kejiang.canyin.fragment.R;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenqun on 2017/7/24.
 * 主页面
 */

public class MainActivity extends BaseActivity{
	// 选项卡tab布局
	@BindViews({R.id.rl_homepage,R.id.rl_message,R.id.rl_me})
	public List<RelativeLayout> relativeLayoutList;
	@OnClick(R.id.rl_homepage)
	public  void homePage(){
		clickTab1Layout();
	}
	@OnClick(R.id.rl_message)
	public  void message(){
		clickTab2Layout();
	}
	@OnClick(R.id.rl_me)
	public  void me(){
		clickTab3Layout();
	}

	@BindColor(R.color.cyan) int cyan;
	@BindColor(R.color.grey) int grey;



	// 底部标签切换的Fragment
	private Fragment homePageFragment, messagesFragment, meFragment,
			currentFragment;

	// 底部标签图片
	@BindViews({R.id.iv_homepage,R.id.iv_message,R.id.iv_me})
	public  List<ImageView> imageViewList;

	// 底部标签的文本
	@BindViews({R.id.tv_homepage,R.id.tv_message,R.id.tv_me})
	public List<TextView> textViewList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initViews();

	}

	@Override
	protected void initViews() {
		initTab();
	}


	/**
	 * 初始化底部标签
	 */
	private void initTab() {
		if (homePageFragment == null) {
            homePageFragment = new HomePageFragment();
		}

		if (!homePageFragment.isAdded()) {// 如果当前fragment未被添加，则添加到Fragment管理器中
			// 提交事务
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content_layout, homePageFragment).commit();

			// 记录当前Fragment
			currentFragment = homePageFragment;
			// 设置图片文本的变化
            imageViewList.get(0).setImageResource(R.drawable.btn_homepage_pre);
            textViewList.get(0).setTextColor(cyan);

            imageViewList.get(1).setImageResource(R.drawable.btn_message_nor);
            textViewList.get(1).setTextColor(grey);

			imageViewList.get(2).setImageResource(R.drawable.btn_my_nor);
			textViewList.get(2).setTextColor(grey);

		}

	}

	/**
	 * 点击首页tab
	 */
	private void clickTab1Layout() {
		if (homePageFragment == null) {
            homePageFragment = new HomePageFragment();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(), homePageFragment);
		
		// 设置底部tab变化
        imageViewList.get(0).setImageResource(R.drawable.btn_homepage_pre);
        textViewList.get(0).setTextColor(cyan);

        imageViewList.get(1).setImageResource(R.drawable.btn_message_nor);
        textViewList.get(1).setTextColor(grey);

		imageViewList.get(2).setImageResource(R.drawable.btn_my_nor);
		textViewList.get(2).setTextColor(grey);
	}

	/**
	 * 点击第二个tab
	 */
	private void clickTab2Layout() {
		if (messagesFragment == null) {
            messagesFragment = new MessageFragment();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(), messagesFragment);

        imageViewList.get(0).setImageResource(R.drawable.btn_homepage_nor);
        textViewList.get(0).setTextColor(grey);
        imageViewList.get(1).setImageResource(R.drawable.btn_message_pre);
        textViewList.get(1).setTextColor(cyan);
		imageViewList.get(2).setImageResource(R.drawable.btn_my_nor);
		textViewList.get(2).setTextColor(grey);

	}

	/**
	 * 点击第三个tab
	 */
	private void clickTab3Layout() {
		if (meFragment == null) {
			meFragment = new MeFragment();
		}
		
		addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);
        imageViewList.get(0).setImageResource(R.drawable.btn_homepage_nor);
        textViewList.get(0).setTextColor(grey);
        imageViewList.get(1).setImageResource(R.drawable.btn_message_nor);
        textViewList.get(1).setTextColor(grey);
		imageViewList.get(2).setImageResource(R.drawable.btn_my_pre);
		textViewList.get(2).setTextColor(cyan);
		
	}

	/**
	 * 添加或者显示碎片
	 * 
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
