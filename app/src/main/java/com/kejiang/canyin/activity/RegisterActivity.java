package com.kejiang.canyin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kejiang.canyin.base.BaseActivity;
import com.kejiang.canyin.fragment.R;
import com.kejiang.canyin.util.NumberUtil;
import com.kejiang.canyin.util.CountTimer;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.kejiang.canyin.fragment.R.string.register;

/**
 * Created by chenqun on 2017/7/27.
 */

public class RegisterActivity extends BaseActivity{
    //头部
    @BindView( R.id.rl_back )
    public RelativeLayout rl_back;
    @BindViews( {R.id.tv_title, R.id.tv_end} )
    public List<TextView> textViewList;

    //注册  登陆界面  明文
    @BindViews({R.id.btn_eye, R.id.btn_again_eye,R.id.btn_register})
    public  List<ImageButton> imageButtonList;

    //用户输入信息控件
    @BindViews({R.id.et_userphone, R.id.et_phonecode, R.id.et_password, R.id.et_again_password})
   public  List<EditText> editTextList;

    //获取验证码
    @BindView(R.id.tv_getphonecode)
    public TextView tv_getphonecode;

//点击事件
    @OnClick(R.id.rl_back)
    public  void back(){
        finish();
    }
    @OnClick(R.id.btn_eye)
    public void  btnEye(){
        eye();
    }
    @OnClick(R.id.btn_again_eye)
    public void btnAgainEye(){
        againEye();
    }
    @OnClick(R.id.btn_register)
    public void btnRegister(){
        register();
    }
    @OnClick(R.id.tv_getphonecode)
    public void getPhoneCode(){
        getCode();

    }

    //用户输入信息
    private String passPhone;
    private String passWord;
    private String phoneCode;
    private String againpassWord;


    //全局控制开关
    boolean eyeOpen = false;
    boolean againEyeOpen = false;

    EventHandler eventHandler;
    //定时器
    CountTimer countTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
            textViewList.get(0).setText(getResources().getString(register));
        }
        if (textViewList.get(1).getVisibility() != View.GONE) {
            textViewList.get(1).setVisibility(View.GONE);
        }
        countTimer=new CountTimer(60000,1000,tv_getphonecode,"重新发送");

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            // 参数2  SMSSDK.RESULT_COMPLETE表示操作成功，SMSSDK.RESULT_ERROR表示操作失败
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);

    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                       showToast("提交验证码成功");
                        //网络请求与提交与界面跳转
                        startActivity( MainActivity.class);

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        showToast("验证码已经发送");
                    } else {
                        showToast("验证码错误");
                        ((Throwable) data).printStackTrace();
                    }
                }
            }

    };


    private void againEye() {
        if (againEyeOpen) {
            //密码 TYPE_CLASS_TEXT 和 TYPE_TEXT_VARIATION_PASSWORD 必须一起使用
            editTextList.get(3).setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageButtonList.get(1).setImageDrawable(getResources().getDrawable(R.drawable.eye_nor));
            againEyeOpen = false;
        } else {
            //明文
            editTextList.get(3).setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageButtonList.get(1).setImageDrawable(getResources().getDrawable(R.drawable.eye_pre));
            againEyeOpen = true;
        }

    }

    private void eye() {
        if (eyeOpen) {
            //密码 TYPE_CLASS_TEXT 和 TYPE_TEXT_VARIATION_PASSWORD 必须一起使用
            editTextList.get(2).setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageButtonList.get(0).setImageDrawable(getResources().getDrawable(R.drawable.eye_nor));
            eyeOpen = false;
        } else {
            //明文
            editTextList.get(2).setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageButtonList.get(0).setImageDrawable(getResources().getDrawable(R.drawable.eye_pre));
            eyeOpen = true;
        }
    }

    private void register() {
        startActivity(MainActivity.class);
        passPhone = editTextList.get(0).getText().toString().trim();
        phoneCode = editTextList.get(1).getText().toString().trim();
        passWord = editTextList.get(2).getText().toString().trim();
        againpassWord = editTextList.get(3).getText().toString().trim();
        if (!NumberUtil.isNull(passPhone)){
            if (!NumberUtil.isNull(phoneCode)){
                if (!NumberUtil.isNull(passWord)){
                    if (!NumberUtil.isNull(againpassWord)){
                        if (passWord!=againpassWord){
                            if (NumberUtil.validatePhone(passPhone)){
                                if (NumberUtil.validatePwd(passWord)){
                                    SMSSDK.submitVerificationCode("86", passPhone,
                                            phoneCode.toString());
                                    //加载进度条
                                    //createProgressBar();


                                }else {
                                    showToast("密码格式错误");
                                }

                            }else {
                                showToast("手机号格式错误");
                            }

                        }else{
                            showToast("密码不一致");
                        }

                    }else{
                        showToast("重复密码不能为空");
                    }

                }else {
                    showToast("密码不能为空");
                }

            }else{
                showToast("验证码不能为空");
            }

        }else {
            showToast("手机号不能为空");
        }
    }

    private void getCode() {
        // 1. 通过规则判断手机号
        passPhone = editTextList.get(0).getText().toString().trim();
        if (!NumberUtil.isNull(passPhone)){
            if (NumberUtil.validatePhone(passPhone)) {
                // 2. 通过sdk发送短信验证
                SMSSDK.getVerificationCode("86", passPhone);
                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                countTimer.start();
            }else {
                showToast("手机号格式错误");
            }

        }else {
            showToast("手机号不能为空");
        }

    }





    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


}
