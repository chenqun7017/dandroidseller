package com.kejiang.canyin.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.R;
import com.kejiang.canyin.view.AbstractSpinerAdapter;
import com.kejiang.canyin.view.SpinerPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenqun on 2017/8/1.
 *
 *
 */



public class AddMenuActivity extends BaseActivity implements AbstractSpinerAdapter.IOnItemSelectListener {

    //头部控件
    @BindView(R.id.rl_back)
    public RelativeLayout rl_back;
    @BindViews({R.id.tv_title, R.id.tv_end})
    public List<TextView> textViewList;

    //添加相册
    @BindView(R.id.ll_photo)
    public LinearLayout ll_photo;

    //一级分类下拉列表
    @BindView(R.id.tv_spiner)
    public TextView tv_spiner;

    //选取的头像
    @BindView(R.id.rv_show_photo)
    public ImageView rv_show_photo;

    //用户选择器
    @BindViews({R.id.check_promotion, R.id.check_false})
    public  List<CheckBox> checkBoxList;

    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }

    @OnClick(R.id.ll_photo)
    public  void phone(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }


    @OnClick(R.id.tv_spiner)
    public void spinner(){
        showSpinWindow();
    }

    @BindString(R.string.addmenu)
    public  String addmenu;

    //自定义下拉框
    private SpinerPopWindow mSpinerPopWindow;

    private List<String> nameList = new ArrayList<String>();

    //调用系统相册-选择图片
    private static final int IMAGE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void initViews() {
        checkBoxList.get(0).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                }else {
                }
            }
        });

        checkBoxList.get(1).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                }else {
                }
            }
        });

        if (rl_back.getVisibility() != View.VISIBLE) {
            rl_back.setVisibility(View.VISIBLE);
        }
        if (textViewList.get(0).getVisibility() != View.VISIBLE) {
            textViewList.get(0).setVisibility(View.VISIBLE);
            textViewList.get(0).setText(addmenu);
        }
        if (textViewList.get(1).getVisibility() != View.GONE) {
            textViewList.get(1).setVisibility(View.GONE);
        }

        nameList.add("小份钱");
        nameList.add("美食");
        nameList.add("健康饮品");
        nameList.add("食堂外卖");

        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.refreshData(nameList, 0);
        mSpinerPopWindow.setItemListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        rv_show_photo.setImageBitmap(bm);
    }

    private void showSpinWindow(){
        mSpinerPopWindow.setWidth(tv_spiner.getWidth());
        mSpinerPopWindow.showAsDropDown(tv_spiner);
    }

    private void setHero(int pos){
        if (pos >= 0 && pos <= nameList.size()){
            String value = nameList.get(pos);
            tv_spiner.setText(value);
        }
    }

    @Override
    public void onItemClick(int pos) {
        setHero(pos);
    }

}
