package com.shangyi.supplier.ui;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shangyi.supplier.R;
import com.shangyi.supplier.config.MyApplication;
import com.shangyi.supplier.entity.User;
import com.shangyi.supplier.entity.UserCallBack;
import com.shangyi.supplier.log.MyLog;
import com.shangyi.supplier.util.ToastUtil;
import com.weavey.loading.lib.LoadingLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static android.R.attr.versionName;

@RuntimePermissions
public class MainActivity extends AppCompatActivity{
    private Context mContext;
    private DownloadManager downloadManager;
    private long mTaskId;
    private String versionName="qq.apk";
    private String versionUri="http://gdown.baidu.com/data/wisegame/62474124707e78ad/QQ_432.apk";
    private String downloadPath;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadingLayout loadingLayout=(LoadingLayout)findViewById(R.id.loading_layout);
        mContext=this;
        loadingLayout.setEmptyText("没有数据");
        loadingLayout.setNoNetworkText("没有网络");

        findViewById(R.id.tv_sina_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShareActivity.class));
                overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
            }
        });

        final String loginUrl="https://ggschool.sayimo.cn/clientapi/"+"provider/providerlogin?providerAccout="+"18696855784"+"&providerPassWord="+"222222"+"&deviceToken="+"AjD0BlcUEQMMJeN32G24VFRwmnRn3guinoN1XeBMgVdR"+"&deviceType=1";
        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              login();
            }
        });
        findViewById(R.id.tv_down_file_apk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivityPermissionsDispatcher.downApkFileWithCheck(MainActivity.this,versionUri,versionName);//http://blog.csdn.net/abbott_133/article/details/51754993
            }
        });
        findViewById(R.id.tv_intent_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*startActivity(new Intent(MainActivity.this,SecondActivity.class));
                overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);*/
                //TODO 未做
                ToastUtil.defaultToast(MyApplication.getInstance(),"留着后面用");
            }
        });
        listView=(ListView)findViewById(R.id.listView1);
        String[] ls = getResources().getStringArray(R.array.anim_type);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < ls.length; i++) {
            list.add(ls[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                switch (position) {
                    case 0:
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        ToastUtil.defaultToast(MyApplication.getInstance(),"0");
                        break;
                    case 1:
                        overridePendingTransition(R.anim.my_scale_action,
                                R.anim.my_alpha_action);
                        ToastUtil.defaultToast(MyApplication.getInstance(),"1");
                        break;
                    case 2:
                        overridePendingTransition(R.anim.scale_rotate,
                                R.anim.my_alpha_action);
                        ToastUtil.defaultToast(MyApplication.getInstance(),"2");
                        break;
                    case 3:
                        overridePendingTransition(R.anim.scale_translate_rotate,
                                R.anim.my_alpha_action);
                        ToastUtil.defaultToast(MyApplication.getInstance(),"3");
                        break;
                    case 4:
                        overridePendingTransition(R.anim.scale_translate,
                                R.anim.my_alpha_action);
                        ToastUtil.defaultToast(MyApplication.getInstance(),"4");
                        break;
                    case 5:
                        overridePendingTransition(R.anim.hyperspace_in,
                                R.anim.hyperspace_out);
                        ToastUtil.defaultToast(MyApplication.getInstance(),"5");
                        break;
                    case 6:
                        overridePendingTransition(R.anim.push_left_in,
                                R.anim.push_left_out);
                        ToastUtil.defaultToast(MyApplication.getInstance(),String.valueOf(position));
                        break;

                    case 7:
                        overridePendingTransition(R.anim.push_up_in,
                                R.anim.push_up_out);
                        ToastUtil.defaultToast(MyApplication.getInstance(),String.valueOf(position));
                        break;
                    case 8:
                        overridePendingTransition(R.anim.slide_left,
                                R.anim.slide_right);
                        ToastUtil.defaultToast(MyApplication.getInstance(),String.valueOf(position));
                        break;
                    case 9:
                        overridePendingTransition(R.anim.wave_scale,
                                R.anim.my_alpha_action);
                        ToastUtil.defaultToast(MyApplication.getInstance(),String.valueOf(position));
                        break;
                    case 10:
                        overridePendingTransition(R.anim.zoom_enter,
                                R.anim.zoom_exit);
                        ToastUtil.defaultToast(MyApplication.getInstance(),String.valueOf(position));
                        break;
                    case 11:
                        overridePendingTransition(R.anim.slide_up_in,
                                R.anim.slide_down_out);
                        ToastUtil.defaultToast(MyApplication.getInstance(),String.valueOf(position));
                        break;

                    default:
                        break;
                }
            }
        });


    }

    /**
     * 下载文件
     */
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void downApkFile(String versionUrl,String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir("/download/", versionName);
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        mContext.registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationaleDownApk(PermissionRequest request) {
        showRationaleDialog("下载Apk需要权限", request);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void turnDownApk() {
        ToastUtil.defaultToast(MyApplication.getInstance(), "拒绝权限将无法下载");
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void juJueAndNoTiShi() {
        ToastUtil.defaultToast(MyApplication.getInstance(), "您拒绝授予权限且不再提示授权");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(MainActivity.this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }



    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };
    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    MyLog.i(">>>下载暂停");
                case DownloadManager.STATUS_PENDING:
                    MyLog.i(">>>下载延迟");
                case DownloadManager.STATUS_RUNNING:
                    MyLog.i(">>>正在下载");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    MyLog.i(">>>下载完成");
                    //下载完成安装APK
                    downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + versionName;
                    installAPK(new File(downloadPath));
                    break;
                case DownloadManager.STATUS_FAILED:
                    MyLog.i(">>>下载失败");
                    break;
            }
        }
    }
    //下载到本地后执行安装
    protected void installAPK(File file) {
        if (!file.exists()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
    /**
     * 登录
     */
    private void login() {
        OkHttpUtils
                .post()//
                .url("https://ggschool.sayimo.cn/clientapi/provider/providerlogin?")//
                .addParams("providerAccout", "18696855784")//
                .addParams("providerPassWord", "222222")//
                .addParams("deviceToken","AjD0BlcUEQMMJeN32G24VFRwmnRn3guinoN1XeBMgVdR")
                .addParams("type","1")
                .build()//
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
}
