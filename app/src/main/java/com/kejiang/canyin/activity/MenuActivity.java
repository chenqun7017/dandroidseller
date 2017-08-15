package com.kejiang.canyin.activity;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.adapter.MenuLeftAdapter;
import com.kejiang.canyin.adapter.MenuRightAdapter;
import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends BaseActivity{

    //头部控件
    @BindView(R.id.rl_back)
    public RelativeLayout rl_back;
    @BindViews({R.id.tv_title, R.id.tv_end})
    public List<TextView> textViewList;

    @BindViews({R.id.leftlist, R.id.rightlist})
    public List<ListView> listViewList;

    @BindView(R.id.auto_search)
    public EditText auto_search;

    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }
    @OnClick(R.id.tv_end)
    public  void end(){
        startActivity(AddMenuActivity.class);
    }


    private String navig[] = new String[]{
            "热销", "折扣", "小分菜", "美食", "饮品", "外卖"};
    private int images[] = new int[]{R.drawable.hot, R.drawable.dis};

    private MenuLeftAdapter menuLeftAdapter;
    private MenuRightAdapter menuRightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind( this );
        initViews();

    }

    @Override
    protected void initViews() {
        if (rl_back.getVisibility() != View.VISIBLE) {
            rl_back.setVisibility(View.VISIBLE);
        }
        if (textViewList.get(0).getVisibility() != View.VISIBLE) {
            textViewList.get(0).setVisibility(View.VISIBLE);
            textViewList.get(0).setText(getResources().getString(R.string.menumag));
        }
        if (textViewList.get(1).getVisibility() != View.VISIBLE) {
            textViewList.get(1).setVisibility(View.VISIBLE);
            textViewList.get(1).setText(getResources().getString(R.string.newmenu));
            textViewList.get(1).setTextColor(ContextCompat.getColor(this, R.color.white));
        }

        menuLeftAdapter = new MenuLeftAdapter(MenuActivity.this, navig, images);
        menuLeftAdapter.setSelectedPosition(0);

        listViewList.get(0).setAdapter(menuLeftAdapter);
        listViewList.get(0).setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                final int location = arg2;
                menuLeftAdapter.setSelectedPosition(arg2);
                menuLeftAdapter.notifyDataSetInvalidated();
                menuRightAdapter = new MenuRightAdapter(MenuActivity.this, arg2);
                listViewList.get(1).setAdapter(menuRightAdapter);
                listViewList.get(1).setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        menuRightAdapter.setSelectedPosition(position);
                    }
                });

            }
        });

        setDefault();



    }


    private void setDefault() {
        final int location = 0;
        menuRightAdapter = new MenuRightAdapter(MenuActivity.this, 0);
        menuRightAdapter.setSelectedPosition(0);
        listViewList.get(1).setAdapter(menuRightAdapter);
        listViewList.get(1).setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                menuRightAdapter.setSelectedPosition(arg2);
            }
        });
    }


}
