package com.kejiang.canyin.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

/**
 * 常用方法工具
 *
 * Created by chenqun on 2017/8/1.
 */


@SuppressLint("SimpleDateFormat")
public class NumberUtil {

	public static String local = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/";


		/**
	 * 获取版本号
	 *
	 * @param context
	 * @return
	 */
	public static int getVerCode(Context context) {
		int verCode = 0;
		try {
			verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 判断 一个字段的值否为空
	 *
	 * @param
	 * @return
	 */
	public static boolean isNull(String s) {
		if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
			return true;
		}

		return false;
	}

	/**
	 * 验证身份证号码
	 *
	 * @param idCard
	 * @return
	 */
	public static boolean validateIdCard(String idCard) {
		if (isNull(idCard))
			return false;
		String pattern = "^[0-9]{17}[0-9|xX]{1}$";
		return idCard.matches(pattern);
	}


	/**
	 * 验证呢称
	 *
	 * @param nickname
	 * @return
	 */
	public static boolean validateNickname(String nickname) {
		if (isNull(nickname))
			return false;
		String pattern = "[\u4e00-\u9fa5\\w]+";
		return nickname.matches(pattern);
	}

	/**
	 * 验证手机号码
	 *
	 * @param phone
	 * @return
	 */
	public static boolean validatePhone(String phone) {
		if (isNull(phone))
			return false;
		String pattern = "^(1(([35][0-9])|(47)|[8][0-9]|[7][0-9]))\\d{8}$";
		return phone.matches(pattern);
	}

	/**
	 * 验证密码
	 *
	 * @param password
	 * @return
	 */
	public static boolean validatePwd(String password) {
		if (isNull(password))
			return false;
		String pattern = "^[a-zA-Z0-9]{6,16}$";
		return password.matches(pattern);
	}


	/**
	 * 判断是否有网络
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {

		final ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean mbEdgeConn = false;
		final NetworkInfo[] info = manager.getAllNetworkInfo();
		for (int i = 0; i < info.length; ++i) {
			if (info[i].getState().equals(NetworkInfo.State.CONNECTED)) {

				String value = info[i].getTypeName();
				if ((info[i].getTypeName().equalsIgnoreCase("mobile"))
						&& (info[i].getSubtypeName().equalsIgnoreCase("EDGE"))) {
					mbEdgeConn = true;
				}
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断当前网络是否是wifi网络.
	 *
	 * @param context
	 * @return boolean
	 * @author Michael.Zhang 2014-3-26 11:31:10
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		return (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI);
	}

	/**
	 * 判断当前网络是否是3G网络.
	 *
	 * @param context
	 * @return boolean
	 * @author Michael.Zhang 2014-3-26 11:31:10
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		return (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE);
	}

}