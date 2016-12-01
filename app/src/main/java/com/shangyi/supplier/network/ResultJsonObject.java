package com.shangyi.supplier.network;

import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by Vincent on 2016/12/1.
 */

public abstract class ResultJsonObject extends Callback<JSONObject> {
    @Override
    public JSONObject parseNetworkResponse(Response response, int id) throws Exception {
        JSONObject jsonObject=new JSONObject(response.body().string());
        return jsonObject;
    }
}
