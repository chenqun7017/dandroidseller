package com.kejiang.canyin.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.kejiang.canyin.fragment.R;
import com.kejiang.canyin.util.SystemBar;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Stack;


/**
 * Created by chenqun on 2017/7/24.
 * 所有Activity的基类
 */
public abstract class BaseActivity extends FragmentActivity {
    private SystemBar tintManager;

    public  BaseApplication mApplication;

    /*** 创建存放activity 椎 */
    private static Stack<BaseActivity> sActivities = new Stack<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(R.color.cyan);//设置状态栏颜色值
        mApplication = (BaseApplication) getApplication();
        //禁止activity横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /** 初始化视图 **/
    protected abstract void initViews();


    /**
     * 界面Activity入栈
     * @param activity
     */
    private static void addActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }
        sActivities.push(activity);
    }

    /**
     * 获取acitivty栈
     * @return
     */
    public static Stack<BaseActivity> getActivityStack() {
        return sActivities;
    }

    /**
     * 获取栈顶界面Activity
     */
    public static BaseActivity getTopActivity() {
        if (sActivities.empty()) {
            return null;
        } else {
            return sActivities.peek();
        }
    }

    /**
     * 界面Activity移出栈
     *
     * @param activity
     */
    private static void removeActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }

        if (sActivities.contains(activity)) {
            sActivities.remove(activity);
        }
    }

    /**
     * 关闭客户端
     */
    public static void closeApplication() {
        if (!sActivities.empty()) {
            for (BaseActivity activity : sActivities) {
                if (activity != null && !activity.isFinishing())
                    activity.finish();
            }
            sActivities.clear();
        }
    }
    /** 短暂显示Toast提示(来自String) **/
    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /** 长时间显示Toast提示(来自String) **/
    protected void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    /** 通过Class跳转界面 **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);

    }

    /** 含有Bundle通过Class跳转界面 **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        removeActivity(this);
        super.onDestroy();
        /*if (null != mRequestQueue) {
            mRequestQueue.stop(); // 停止网络访问
            mRequestQueue.cancelAll(this); // 取消队列中的所有请求
            mRequestQueue = null;
        }*/
    }

    public void setStatusBarColor(int resId) {
        /** android:fitsSystemWindows="true" */
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        /** 设置状态栏所需颜色 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            tintManager = new SystemBar(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(resId);// 状态栏所需颜色
        }
    }

    public void setNoShowStatusBar() {
        tintManager.setStatusBarTintEnabled(false);
    }

    /**
     * Make status translucent
     */
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * @param translucent_status     是否透明状态栏
     * @param translucent_navigation 是否透明导航栏
     */
    public void setTranslateTheme(boolean translucent_status, boolean translucent_navigation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (translucent_status)
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (translucent_navigation)
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 获取错误的信息
     *
     * @param arg1
     * @return
     */
    public String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }

}
