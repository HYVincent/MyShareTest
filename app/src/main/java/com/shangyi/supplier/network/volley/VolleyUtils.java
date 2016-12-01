package com.shangyi.supplier.network.volley;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shangyi.supplier.config.MyApplication;

import org.json.JSONObject;

import java.util.Map;

public class VolleyUtils {
    public static final String TAG = "VolleyPatterns";

    private static RequestQueue mRequestQueue;

    //public static final String SERVER_URL = "http://192.168.0.18/";
    public static final String SERVER_URL_1 = "http://192.168.0.18/";
    public static final String SERVER_URL = "http://120.25.146.90/";

    public static RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(MyApplication.getInstance());
        }
        return mRequestQueue;
    }

    public static void addToRequestQueue(String tag, Request req) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        //设置超时
        req.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleyLog.d("请求URL: %s", req.getUrl());
        getRequestQueue().add(req);
    }

    public static void addToRequestQueue(Request req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        VolleyLog.d("请求URL: %s", req.getUrl());
        getRequestQueue().add(req);
    }

    /**
     * 根据tag取消request
     */
    public static void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * 取消所有request
     */
    public static void cancelAllRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(MyApplication.getInstance());
        }
    }


    /**
     * get方式StringRequest
     * 返回数据为String
     */
    public static void getStringRequest(String tag, String url,
                                        Response.Listener<String> onResponse,
                                        Response.ErrorListener onErrorResponse) {
        StringRequest req = new StringRequest(Method.GET, url, onResponse, onErrorResponse);
        // add the request object to the queue to be executed
        addToRequestQueue(tag, req);
    }

    /**
     * post方式StringRequest
     * 返回数据为String
     */
    public static void postStringRequest(String tag, String url,
                                         final Map<String, String> paramMap,
                                         Response.Listener<String> onResponse,
                                         Response.ErrorListener onErrorResponse) {
        StringRequest req = new StringRequest(Method.POST, url, onResponse,
                onErrorResponse) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paramMap;
            }
        };

        // add the request object to the queue to be executed
        addToRequestQueue(tag, req);
    }


    /**
     * get方式JsonObjectRequest
     * 返回数据为JsonObject
     */
    public static void getJsonObjectRequest(String tag, String url,
                                            Response.Listener<JSONObject> onResponse,
                                            Response.ErrorListener onErrorResponse) {
        JsonObjectRequest req = new JsonObjectRequest(url, null, onResponse, onErrorResponse);
        // add the request object to the queue to be executed
        addToRequestQueue(tag, req);
    }


    /**
     * 提交一个post请求,如果有参数必须以JSONObject的json串方式提交.
     * 返回数据为JsonObject
     */
    public static void postJsonObjectRequest(String tag, String url,
                                             Map<String, String> paramMap,
                                             Response.Listener<JSONObject> onResponse,
                                             Response.ErrorListener onErrorResponse) {

        JSONObject params = new JSONObject(paramMap);
        JsonObjectRequest req = new JsonObjectRequest(url, params, onResponse, onErrorResponse);
        // add the request object to the queue to be executed
        addToRequestQueue(tag, req);
    }


    /**
     * 提交一个post请求,如果有参数必须以普通的post方式进行提交
     * 返回数据为String
     */
    public static void postStringNormalRequest(String tag, String url,
                                               Map<String, String> paramMap,
                                               Response.Listener<String> onResponse,
                                               Response.ErrorListener onErrorResponse) {

        NormalStringRequest req = new NormalStringRequest(url, onResponse, onErrorResponse, paramMap);
        // add the request object to the queue to be executed
        addToRequestQueue(tag, req);
    }


    /**
     * 提交一个post请求,如果有参数必须以普通的post方式进行提交
     * 返回数据为JsonObject
     */
    public static void postJSONObjectNormalRequest(String tag, String url,
                                                   Map<String, String> paramMap,
                                                   Response.Listener<JSONObject> onResponse,
                                                   Response.ErrorListener onErrorResponse) {
        try {
            JNormalRequest req = new JNormalRequest(url, onResponse, onErrorResponse, paramMap);
            addToRequestQueue(tag, req);
        }catch (Exception e){
            e.printStackTrace();
        }
        // add the request object to the queue to be executed

    }


    /**
     * 提交一个post请求,如果有参数必须以普通的post方式进行提交
     * 返回数据为JsonObject
     */
   /* public static void postJSONArrayNormalRequest(String tag, String url,
                                                   Map<String, String> paramMap,
                                                   Response.Listener<List<FootmarkEntity>> onResponse,
                                                   Response.ErrorListener onErrorResponse) {

        NormalJSONArrayRequest req = new NormalJSONArrayRequest(url, onResponse, onErrorResponse, paramMap);

        // add the request object to the queue to be executed
        addToRequestQueue(tag, req);
    }
*/

    /**
     * 上传图片，使用Volley
     */
   /* public static void uploadListFileRequest(final String tag,
                                             final String url,
                                             final List<File> files,
                                             final int contentType,
                                             final Map<String, String> params,
                                             final Response.Listener<String> responseListener,
                                             final Response.ErrorListener errorListener) {
        if (null == url || null == responseListener) {
            return;
        }

        MultipartRequest multiPartRequest = new MultipartRequest(url, errorListener, responseListener, contentType, files, params);


        //JLog.i(TAG, " volley put : uploadFile " + url);
        addToRequestQueue(tag, multiPartRequest);
    }*/


    /**
     * post方式JsonObjectRequest
     */
    public static void postXMLRequest(String tag, String url,
                                      Map<String, String> paramMap,
                                      Response.Listener<JSONObject> onResponse,
                                      Response.ErrorListener onErrorResponse) {

        JSONObject params = new JSONObject(paramMap);
        JsonObjectRequest req = new JsonObjectRequest(url, params, onResponse,
                onErrorResponse);
        // add the request object to the queue to be executed
        addToRequestQueue(tag, req);
    }


    /**
     * 点赞
     */
    public static void praiseFootmark(String feedId){


    }

}
