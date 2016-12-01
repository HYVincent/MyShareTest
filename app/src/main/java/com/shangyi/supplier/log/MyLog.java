
package com.shangyi.supplier.log;


import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * 作者：david on 2016/3/22 10:14
 * <p/>
 * 联系QQ：986945193
 * <p/>
 * 微博：http://weibo.com/mcxiaobing
 */

public final class MyLog {

/** logSwitch Log print on-off */

	private static boolean logSwitch=false;

/**
	 * MyLog 日志开关控制
	 * @param a false 日志关闭 true日志开启，默认关闭，需要在初始化之后手动设置
	 * @return
     */

	public static boolean setSwitch(boolean a) {
		logSwitch=a;
		return a;
	}


/**
	 * 初始化，必须在继承Application的类中初始化，初始化成功之后方可使用
	 * @param tab
     */

	public static void init(String tab){
		Logger.init(tab);
	}


/** info Log print on-off */

	private final static boolean i = true;

/** debug Log print on-off */

	private final static boolean d = true;

/** err Log print on-off */

	private final static boolean e = true;
/** verbose Log print on-off */

	private final static boolean v = true;
/** warn Log print on-off */

	private final static boolean w = true;
/** default print tag */

	private final static String defaultTag = "LogUtil-->";
	
	private MyLog() throws UnsupportedOperationException {

	};

/**
	 * info Log print,default print tag
	 * 
	 * @param msg
	 *            :print message
	 */

	public static void i(String msg) {
		if (logSwitch && i) {
//			android.util.Log.i(defaultTag, msg);
			Logger.i(defaultTag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * info Log print
	 * 
	 * @param tag
	 *            :print tag
	 * @param msg
	 *            :print message
	 */

	public static void i(String tag, String msg) {
		if (logSwitch && i) {
//			android.util.Log.i(tag, msg);
			Logger.i(tag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * debug Log print,default print tag
	 * 
	 * @param msg
	 *            :print message
	 */

	public static void d(String msg) {
		if (logSwitch && d) {
//			android.util.Log.d(defaultTag, msg);
			Logger.d(defaultTag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * debug Log print
	 * 
	 * @param tag
	 *            :print tag
	 * @param msg
	 *            :print message
	 */

	public static void d(String tag, String msg) {
		if (logSwitch && d) {
//			android.util.Log.d(tag, msg);
			Logger.d(tag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * err Log print,default print tag
	 * 
	 * @param msg
	 *            :print message
	 */

	public static void e(String msg) {
		if (logSwitch && e) {
			try {
//				android.util.Log.e(defaultTag, msg);
				Logger.e(defaultTag+","+msg);
			} catch (Exception e1) {
				// TOdO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * err Log print
	 * 
	 * @param tag
	 *            :print tag
	 * @param msg
	 *            :print message
	 */

	public static void e(String tag, String msg) {
		if (logSwitch && e) {
//			android.util.Log.e(tag, msg);
			Logger.e(tag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * verbose Log print,default print tag
	 * 
	 * @param msg
	 *            :print message
	 */

	public static void v(String msg) {
		if (logSwitch && v) {
//			android.util.Log.v(defaultTag, msg);
			Logger.v(defaultTag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * verbose Log print
	 * 
	 * @param tag
	 *            :print tag
	 * @param msg
	 *            :print message
	 */

	public static void v(String tag, String msg) {
		if (logSwitch && v) {
//			android.util.Log.v(tag, msg);
			Logger.v(defaultTag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * warn Log print,default print tag
	 * 
	 * @param msg
	 *            :print message
	 */

	public static void w(String msg) {
		if (logSwitch && w) {
//			android.util.Log.w(defaultTag, msg);
			Logger.w(defaultTag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}


/**
	 * warn Log print
	 * 
	 * @param tag
	 *            :print tag
	 * @param msg
	 *            :print message
	 */

	public static void w(String tag, String msg) {
		if (logSwitch && w) {
//			android.util.Log.w(tag, msg);
			Logger.w(defaultTag+","+msg);
		}else {
			Log.d("Julie","What are you doing?");
		}
	}

}

