package com.shangyi.supplier.config;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 项目名称：MyTest
 * 类描述：
 * 类信息：Vincent QQ1032006226
 * 创建时间：2016/11/30 11:17
 * 修改备注：Vincent
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
    }

    public static synchronized MyApplication getInstance() {
        return myApplication;
    }

    public static int getScreenParameterWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) getInstance().getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕宽度（像素）
        int w = width + height;
        return width;
    }

    public static int getScreenParameterHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) getInstance().getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕宽度（像素）
        int w = width + height;
        return height;
    }
}
