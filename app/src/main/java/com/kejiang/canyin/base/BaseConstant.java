package com.kejiang.canyin.base;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqun on 2017/7/24.
 * App全局变量
 */

public class BaseConstant {

	/** 用户登陆成功后保存信息 */
	public static String uid;
	public static String username;
	public static String password;

	public static final String[] NUMS = { "一", "二", "三", "四", "五", "六",
			"七", "八", "九","十"};

	//用户输入数据
	public static List price=new ArrayList();
	public static List getPrice() {
		return price;
	}



	/** 定位*/
	public static String locationAddress="";


	/** 用户登陆状态 */
	public static boolean USERLOGIN = false;

	/** 无网络错误信息 */
	public static final String NO_NETWORK_MESSAGE = "暂无网络";

	/** 请求超时错误信息 */
	public static final String REQUEST_TIMEOUT_MESSAGE = "网络不给力，请稍后再试";

	/** 请求超时返回判断 */
	public static final String REQUEST_TIMEOUT_EEOR = "org.apache.http.conn.ConnectTimeoutException: Connect to /115.28.238.60:80 timed out";

	/** 请求数据超时毫秒数 */
	public static final int REQUEST_TIMEOUT_TIME = 5000;
}
