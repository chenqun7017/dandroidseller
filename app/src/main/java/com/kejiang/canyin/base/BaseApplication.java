package com.kejiang.canyin.base;

import android.app.Activity;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mob.MobApplication;

/**
 * APP全局配置
 */
public class BaseApplication extends MobApplication {

	//Volley的全局请求队列
	public static RequestQueue sRequestQueue;

	//实例化SharedPreferences存储数据
	public static SharedPreferences sharedPreferences;

	//SharedPreferences.Editor editor = sharedPreferences.edit();
	//用putString的方法保存数据
	//editor.putString("name", "Karl");
	//editor.putString("habit", "sleep");
	//提交当前数据
	//editor.commit();
/*	String name =sharedPreferences.getString("name", "");
	String habit =sharedPreferences.getString("habit", "");*/


	public static SharedPreferences getSharedPreferences() {
		return sharedPreferences;
	}

	public static void setSharedPreferences(SharedPreferences sharedPreferences) {
		BaseApplication.sharedPreferences = sharedPreferences;
	}

	/**
	 * @return Volley全局请求队列
	 */
	public static RequestQueue getRequestQueue() {
		return sRequestQueue;
	}


	@Override
	public void onCreate() {
		super.onCreate();
		//实例化Volley全局请求队列
		sRequestQueue = Volley.newRequestQueue(getApplicationContext());
		//实例化SharedPreferences
		sharedPreferences= getSharedPreferences("canyin",
				Activity.MODE_PRIVATE);
	}
}
