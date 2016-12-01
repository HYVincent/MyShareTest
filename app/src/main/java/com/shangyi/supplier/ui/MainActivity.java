package com.shangyi.supplier.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shangyi.supplier.R;
import com.shangyi.supplier.config.MyApplication;
import com.shangyi.supplier.entity.User;
import com.shangyi.supplier.entity.UserCallBack;
import com.shangyi.supplier.log.MyLog;
import com.shangyi.supplier.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_sina_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShareActivity.class));
            }
        });

        final String loginUrl="https://ggschool.sayimo.cn/clientapi/"+"provider/providerlogin?providerAccout="+"18696855784"+"&providerPassWord="+"222222"+"&deviceToken="+"AjD0BlcUEQMMJeN32G24VFRwmnRn3guinoN1XeBMgVdR"+"&deviceType=1";
        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils
                        .post()//
                        .url("https://ggschool.sayimo.cn/clientapi/provider/providerlogin?")//
                        .addParams("providerAccout", "18696855784")//
                        .addParams("providerPassWord", "222222")//
                        .addParams("deviceToken","AjD0BlcUEQMMJeN32G24VFRwmnRn3guinoN1XeBMgVdR")
                        .addParams("type","1")
//                        .url(loginUrl)
                        .build()//
//                        .execute(new UserCallBack(){
//
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//                                ToastUtil.defaultToast(MyApplication.getInstance(),"请求错误");
//                            }
//
//                            @Override
//                            public void onResponse(User user, int id) {
//                                if(user.getStatus().equals("1")){
//                                    ToastUtil.defaultToast(MyApplication.getInstance(),"登录成功");
//                                    MyLog.d("data="+user.getData());
//                                }else {
//                                    ToastUtil.defaultToast(MyApplication.getInstance(),"登录错误"+user.toString());
//                                }
//                            }
//                        });
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.defaultToast(MyApplication.getInstance(),"请求错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ToastUtil.defaultToast(MyApplication.getInstance(),response);
                    }
                });
            }
        });
    }
}
