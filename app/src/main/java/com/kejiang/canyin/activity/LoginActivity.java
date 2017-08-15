package com.kejiang.canyin.activity;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.base.BaseApplication;
import com.kejiang.canyin.fragment.R;
import com.kejiang.canyin.util.NumberUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kejiang.canyin.fragment.R.id.btn_eye;


/**
 * Created by chenqun on 2017/7/24.
 * 登陆界面
 */

public class LoginActivity extends BaseActivity {
    //头部控件
    @BindView( R.id.rl_back )
    public RelativeLayout rl_back;
    @BindViews( {R.id.tv_title, R.id.tv_end} )
    public List<TextView> textViewList;

    //登陆,注册 明文
    @BindViews({R.id.btn_login,R.id.btn_register, btn_eye})
    public List<ImageButton> buttonList;
    @OnClick(R.id.btn_login)
    public  void  login(){
        btnLogin();
    }
    @OnClick(R.id.btn_register)
    public  void  register(){
        startActivity(RegisterActivity.class);
    }
    @BindDrawable(R.drawable.eye_nor)
    public Drawable eye_nor;
    @BindDrawable(R.drawable.eye_pre)
    public Drawable eye_pre;

    @OnClick(R.id.btn_eye)
    public  void eye(){
        btnEye();
    }
    //用户账号密码输入
    @BindViews({R.id.et_userphone, R.id.et_password})
    public List<EditText> editTextList;

    //全局变量
    boolean eyeOpen = false;
    private String userPhone;
    private String passWord;

    //网络请求队列标签
    private String VOLLEY_TAG = "LOGIN";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind( this );
        initViews();
    }

    @Override
    protected void initViews() {
        if (rl_back.getVisibility() != View.GONE) {
            rl_back.setVisibility(View.GONE);
        }
        if (textViewList.get(0).getVisibility() != View.GONE) {
            textViewList.get(0).setVisibility(View.GONE);
        }
        if (textViewList.get(1).getVisibility() != View.GONE) {
            textViewList.get(1).setVisibility(View.GONE);
        }

    }


    private void btnLogin() {
        userPhone = editTextList.get(0).getText().toString().trim();
        passWord = editTextList.get(1).getText().toString().trim();
        if (!NumberUtil.isNull(userPhone) && !NumberUtil.isNull(passWord)) {
            if (NumberUtil.validatePhone(userPhone) && NumberUtil.validatePwd(passWord)) {
                //网络请求
            } else {
                showToast("手机号或官码错误");
                return;
            }

        } else {
            showToast("手机号或密码为空");
            return;
        }
        startActivity(MainActivity.class);
    }

    private void btnEye() {
        if (eyeOpen) {
            //密码 TYPE_CLASS_TEXT 和 TYPE_TEXT_VARIATION_PASSWORD 必须一起使用
            editTextList.get(1).setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            buttonList.get(2).setImageDrawable(eye_nor);
            passWord = editTextList.get(1).getText().toString().trim();
            if (passWord != "") {
                editTextList.get(1).setText(passWord);
                editTextList.get(1).setSelection(passWord.length());
            }
            eyeOpen = false;
        } else {
            //明文
            editTextList.get(1).setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            buttonList.get(2).setImageDrawable(eye_pre);
            passWord = editTextList.get(1).getText().toString().trim();
            if (passWord != "") {
                editTextList.get(1).setText(passWord);
                editTextList.get(1).setSelection(passWord.length());
            }
            eyeOpen = true;
        }

    }
    public  void  ImageLoader(){
/*
    ImageLoader imageLoader = new ImageLoader(mQueue, new ImageCache() {
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
        }

        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }
    });*/
/*

        ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.default_image, R.drawable.failed_image);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                listener, 200, 200);
*/

     /*   ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());*/

    }












    //get
    public void jsonObjectRequest_GET() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "url", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO: 处理返回结果
                Log.i("### onResponse", "GET_JsonObjectRequest:" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: 处理错误
                Log.e("### onErrorResponse", "GET_JsonObjectRequest:" + error.toString());
            }
        });
        //为request设置tag，通过该tag在全局队列中访问request
        jsonObjectRequest.setTag(VOLLEY_TAG);//JsonObjectRequestTest_GET

        BaseApplication.getRequestQueue().add(jsonObjectRequest);
    }

    public void strRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "URL", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("postcode", "");
                paramMap.put("key", "");
                return paramMap;
            }
        };
        stringRequest.setTag(VOLLEY_TAG);
        BaseApplication.getRequestQueue().add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //当Activity停止运行后，取消Activity的所有网络请求
        BaseApplication.getRequestQueue().cancelAll(VOLLEY_TAG);
    }


}