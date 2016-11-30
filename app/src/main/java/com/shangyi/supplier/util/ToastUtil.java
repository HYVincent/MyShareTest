package com.shangyi.supplier.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Vincent on 2016/7/11.
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * 默认Toast 长时间显示
     * @param context
     * @param message
     */
    public static void defaultToast(Context context,String message)
    {
      Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }


    /**
     * 如果当前Toast没有消失，继续点击事件不叠加
     * @param context
     * @param msg
     */
    public static void showSingleTextToast(Context context,String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

}
