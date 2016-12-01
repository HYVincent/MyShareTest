package com.shangyi.supplier.entity;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Vincent on 2016/12/1.
 */

public abstract class UserCallBack extends Callback<User> {
    @Override
    public User parseNetworkResponse(Response response, int id) throws Exception {
        String string=response.body().string();
        User user=new Gson().fromJson(string,User.class);
        return user;
    }
}
