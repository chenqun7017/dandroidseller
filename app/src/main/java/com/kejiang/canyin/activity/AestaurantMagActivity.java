package com.kejiang.canyin.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.R;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kejiang.canyin.fragment.R.id.et_aestaurant_address;
import static com.kejiang.canyin.fragment.R.id.et_aestaurant_name;
import static com.kejiang.canyin.fragment.R.id.et_aestaurant_phone;
import static com.kejiang.canyin.fragment.R.id.et_aestaurant_time;

/**
 * Created by chenqun on 2017/7/28.
 *
 *
 */

public class AestaurantMagActivity extends BaseActivity{
    //头部控件
    @BindView(R.id.rl_back)
    public RelativeLayout rl_back;
    @BindViews({R.id.tv_title, R.id.tv_end})
    public List<TextView> textViewList;
    //餐饮信息
    @BindViews({et_aestaurant_name, et_aestaurant_address,
            et_aestaurant_phone, et_aestaurant_time})
    public List<EditText> editTextList;

    //餐饮类别
    @BindViews({R.id.check_coffee, R.id.check_food, R.id.check_bar, R.id.check_repast})
    public List<CheckBox> checkBoxList;

    @BindView(R.id.profile_image)
    public ImageView  profile_image;

    //编缉
    @BindView(R.id.btn_editor)
    public ImageButton btn_editor;



    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }
    @OnClick(R.id.tv_end)
    public void end(){
        if (STATE_SAVE) {
            aestaurant_name = editTextList.get(0).getText().toString().trim();
            aestaurant_address = editTextList.get(1).getText().toString().trim();
            aestaurant_phone = editTextList.get(2).getText().toString().trim();
            aestaurant_time = editTextList.get(3).getText().toString().trim();

            //进行网络请求
            editTextList.get(0).setEnabled(false);
            editTextList.get(1).setEnabled(false);
            editTextList.get(2).setEnabled(false);
            editTextList.get(3).setEnabled(false);

            btn_editor.setBackgroundResource(R.drawable.editor_nor);

            checkBoxList.get(0).setClickable(false);
            checkBoxList.get(1).setClickable(false);
            checkBoxList.get(2).setClickable(false);
            checkBoxList.get(3).setClickable(false);

            STATE_EDIT = false;
            STATE_SAVE = false;
        }
    }

    @OnClick(R.id.btn_editor)
    public  void btnEditor(){
        if (!STATE_EDIT) {
            btn_editor.setBackgroundResource(R.drawable.editor_pre);
            editTextList.get(0).setEnabled(true);
            editTextList.get(1).setEnabled(true);
            editTextList.get(2).setEnabled(true);
            editTextList.get(3).setEnabled(true);

            checkBoxList.get(0).setClickable(true);
            checkBoxList.get(1).setClickable(true);
            checkBoxList.get(2).setClickable(true);
            checkBoxList.get(3).setClickable(true);

            showToast("true");
            STATE_EDIT = true;
            STATE_SAVE = true;
        } else {

            showToast("false");
        }

    }

    @OnClick(R.id.profile_image)
    public void image(){
        if (STATE_EDIT){
            //调用相册
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMAGE);
        }

    }

    @BindString(R.string.restaurantmag)
    public  String restaurantmag;
    @BindString(R.string.save)
    public String save;



    //获取用户输入内容
    private String aestaurant_name;
    private String aestaurant_address;
    private String aestaurant_phone;
    private String aestaurant_time;
    private String TYPE = "0";

    //编缉时的Button的状态与保存状态
    private boolean STATE_EDIT = false;
    private boolean STATE_SAVE = false;

    //调用系统相册-选择图片
    private static final int IMAGE = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restauranmag);
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
            textViewList.get(0).setText(restaurantmag);
        }
        if (textViewList.get(1).getVisibility() != View.VISIBLE) {
            textViewList.get(1).setVisibility(View.VISIBLE);
            textViewList.get(1).setText(save);

        }

        checkBoxList.get(0).setClickable(false);
        checkBoxList.get(1).setClickable(false);
        checkBoxList.get(2).setClickable(false);
        checkBoxList.get(3).setClickable(false);


        //用户选择餐厅类型
        checkBoxList.get(0).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TYPE = "1";
                    checkBoxList.get(1).setChecked(false);
                    checkBoxList.get(2).setChecked(false);
                    checkBoxList.get(3).setChecked(false);

                }
            }
        });
        checkBoxList.get(1).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TYPE = "2";
                    checkBoxList.get(0).setChecked(false);
                    checkBoxList.get(2).setChecked(false);
                    checkBoxList.get(3).setChecked(false);

                }
            }
        });

        checkBoxList.get(2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TYPE = "3";
                    checkBoxList.get(0).setChecked(false);
                    checkBoxList.get(1).setChecked(false);
                    checkBoxList.get(3).setChecked(false);
                }
            }
        });

        checkBoxList.get(3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TYPE = "4";
                    checkBoxList.get(0).setChecked(false);
                    checkBoxList.get(1).setChecked(false);
                    checkBoxList.get(2).setChecked(false);

                }
            }
        });


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
        profile_image.setImageBitmap(bm);
    }
}